/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.gui.progress;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.i18n4java.Translator;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

import com.puresol.WeakReferenceSet;

/**
 * This class is a simple implementation for a splash screen for application
 * start ups.
 * 
 * @author Rick-Rainer ludwig
 * 
 */
public class ProgressPanel extends JPanel implements ProgressObserver,
		ActionListener, FinishListener {

	private static final long serialVersionUID = 4191554073727049318L;

	private static final Translator translator = Translator
			.getTranslator(ProgressPanel.class);

	private final WeakReferenceSet<FinishListener> finishListeners = new WeakReferenceSet<FinishListener>();

	private final TitledBorder titledBorder = new TitledBorder("");
	private final JProgressBar progressBar = new JProgressBar();
	private final JLabel text = new JLabel();
	private final JButton cancel = new JButton(translator.i18n("Cancel"));

	private ProgressObservable task;
	private Future<?> future = null;
	private boolean finished = false;
	private boolean terminated = false;
	private boolean syncronous = false;

	public ProgressPanel() {
		super();
		initUI();
	}

	private void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		JPanel progressPanel = new JPanel();
		progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.Y_AXIS));
		progressPanel.add(text);
		progressPanel.add(progressBar);

		panel.add(progressPanel);
		panel.add(cancel);
		cancel.addActionListener(this);
		panel.setBorder(titledBorder);

		add(panel);
	}

	public Object getTask() {
		return task;
	}

	public Future<?> getFuture() {
		return future;
	}

	public boolean isFinished() {
		return finished;
	}

	public boolean isTerminated() {
		return terminated;
	}

	@Override
	public void setText(String text) {
		this.text.setText(text);
	}

	@Override
	public void setRange(int min, int max) {
		progressBar.setMinimum(min);
		progressBar.setMaximum(max);
	}

	@Override
	public void setStatus(int value) {
		progressBar.setValue(value);
	}

	@Override
	public void setTitle(String title) {
		titledBorder.setTitle(title);
	}

	@Override
	public void setProgressText(String text) {
		progressBar.setString(text);
		progressBar.setStringPainted(true);
	}

	@Override
	public void showProgressPercent() {
		progressBar.setString(null);
		progressBar.setStringPainted(true);
	}

	public void addFinishListener(FinishListener listener) {
		finishListeners.add(listener);
	}

	public void removeFinishListener(FinishListener listener) {
		finishListeners.remove(listener);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			terminated(task);
		}
	}

	public Future<?> runAsyncronous(RunnableProgressObservable task) {
		this.task = task;
		task.setMonitor(this);
		finished = false;
		terminated = false;
		syncronous = false;
		ExecutorService service = Executors.newSingleThreadExecutor();
		future = service.submit(task);
		service.shutdown();
		return future;
	}

	public Future<?> runAsyncronous(CallableProgressObservable<?> task) {
		this.task = task;
		task.setMonitor(this);
		finished = false;
		terminated = false;
		syncronous = false;
		ExecutorService service = Executors.newSingleThreadExecutor();
		future = service.submit(task);
		service.shutdown();
		return future;
	}

	public synchronized Future<?> runSyncronous(RunnableProgressObservable task)
			throws InterruptedException {
		this.task = task;
		task.setMonitor(this);
		finished = false;
		terminated = false;
		syncronous = true;
		ExecutorService service = Executors.newSingleThreadExecutor();
		future = service.submit(task);
		service.shutdown();
		wait();
		return future;
	}

	public synchronized Future<?> runSyncronous(
			CallableProgressObservable<?> task) throws InterruptedException {
		this.task = task;
		task.setMonitor(this);
		finished = false;
		terminated = false;
		syncronous = true;
		ExecutorService service = Executors.newSingleThreadExecutor();
		future = service.submit(task);
		service.shutdown();
		wait();
		return future;
	}

	@Override
	public ProgressPanel getSubProgressPanel() {
		ProgressPanel progressPanel = new ProgressPanel();
		progressPanel.addFinishListener(this);
		add(progressPanel);
		return progressPanel;
	}

	@Override
	public synchronized void finished(ProgressObservable observable) {
		if (observable == task) {
			future = null;
			finished = true;
			if (syncronous) {
				notify();
			}
			finishAndRemoveAllSubProgressPanels();
			for (FinishListener listener : finishListeners) {
				listener.finished(task);
			}
		} else {
			remove((Component) observable.getMonitor());
		}
	}

	@Override
	public synchronized void terminated(ProgressObservable observable) {
		if (observable == task) {
			future.cancel(true);
			future = null;
			terminated = true;
			if (syncronous) {
				notify();
			}
			finishAndRemoveAllSubProgressPanels();
			for (FinishListener listener : finishListeners) {
				listener.terminated(task);
			}
		} else {
			remove((Component) observable.getMonitor());
			/*
			 * If a sub process was canceled, the whole process stack becomes
			 * invalid and is to be canceled, too.
			 */
			terminated(task);
		}
	}

	/**
	 * Reads all components and stops all sub processes.
	 */
	private void finishAndRemoveAllSubProgressPanels() {
		for (Component component : getComponents()) {
			if (component instanceof ProgressPanel) {
				ProgressPanel panel = (ProgressPanel) component;
				Future<?> future = panel.getFuture();
				if (future != null) {
					future.cancel(true);
				}
			}
			remove(component);
		}
	}
}
