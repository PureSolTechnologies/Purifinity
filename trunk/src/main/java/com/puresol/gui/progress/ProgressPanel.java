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

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.i18n4java.Translator;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * This class provides a simple progress bar panel with advanced functionality
 * for showing the progress of a given thread and sub threads if they are
 * created.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProgressPanel extends JPanel implements ProgressObserver,
		FinishListener, ActionListener {

	private static final long serialVersionUID = -5428306694138966408L;

	private static final Translator translator = Translator
			.getTranslator(ProgressPanel.class);

	private final List<FinishListener> finishListeners = new ArrayList<FinishListener>();

	private final JLabel description = new JLabel();
	private final JLabel label = new JLabel();
	private final JProgressBar progressBar = new JProgressBar();
	private final JButton cancel = new JButton(translator.i18n("Cancel"));

	private boolean ownThreadFinished = false;
	private final ExecutorService thread;
	private final RunnableProgressObservable observable;
	private final List<ProgressPanel> subProgressPanels = new ArrayList<ProgressPanel>();

	public ProgressPanel(RunnableProgressObservable observable) {
		super();
		this.observable = observable;
		observable.setMonitor(this);
		this.thread = Executors.newSingleThreadExecutor();
		initUI();
	}

	private void initUI() {
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
		add(description);
		add(label);
		add(progressBar);
		add(cancel);

		cancel.addActionListener(this);

		progressBar.setStringPainted(true);
		progressBar.setString(null);
		setMinimumSize(new Dimension(400, 100));
	}

	@Override
	public void setRange(int min, int max) {
		progressBar.setMinimum(min);
		progressBar.setMaximum(max);
	}

	@Override
	public void setStatus(int status) {
		progressBar.setValue(status);
	}

	@Override
	public void setText(String text) {
		label.setText(text);
	}

	public void run() {
		setVisible(true);
		thread.submit(observable);
	}

	public void cancel() {
		thread.shutdownNow();
		ownThreadFinished = true;
		finish();
	}

	@Override
	public void finish() {
		ownThreadFinished = true;
		if (isFinished()) {
			sendFinishedSignal();
		}
	}

	private void sendFinishedSignal() {
		for (FinishListener listener : finishListeners) {
			listener.finished(observable);
		}
	}

	public boolean isFinished() {
		synchronized (subProgressPanels) {
			return (ownThreadFinished && (subProgressPanels.size() == 0));
		}
	}

	@Override
	public void setDescription(String description) {
		this.description.setText(description);
	}

	@Override
	public void setProgressText(String text) {
		progressBar.setStringPainted(true);
		progressBar.setString(text);
	}

	@Override
	public void showProgressPercent() {
		progressBar.setStringPainted(true);
		progressBar.setString(null);
	}

	@Override
	public ProgressObserver startSubProgress(RunnableProgressObservable thread) {
		ProgressPanel subProgressPanel = new ProgressPanel(thread);
		synchronized (subProgressPanels) {
			subProgressPanels.add(subProgressPanel);
		}
		add(subProgressPanel);
		subProgressPanel.addFinishListener(this);
		subProgressPanel.run();
		return null;
	}

	public void addFinishListener(FinishListener listener) {
		finishListeners.add(listener);
	}

	public void removeFinishListener(FinishListener listener) {
		finishListeners.remove(listener);
	}

	@Override
	public void finished(Runnable runnable) {
		remove((ProgressPanel) runnable);
		repaint();
		synchronized (subProgressPanels) {
			subProgressPanels.remove((ProgressPanel) runnable);
		}
		if (isFinished()) {
			sendFinishedSignal();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			cancel();
		}
	}
}
