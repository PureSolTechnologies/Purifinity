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

import com.puresol.ListenerSet;

/**
 * This class is a simple implementation for a splash screen for application
 * start ups.
 * 
 * @author Rick-Rainer ludwig
 * 
 */
public class ProgressPanel extends JPanel implements ProgressObserver,
		ActionListener {

	private static final long serialVersionUID = 4191554073727049318L;

	private static final Translator translator = Translator
			.getTranslator(ProgressPanel.class);

	private final ListenerSet<FinishListener> finishListeners = new ListenerSet<FinishListener>();

	private final JProgressBar progressBar = new JProgressBar();
	private final JLabel description = new JLabel();
	private final JLabel text = new JLabel();
	private final JButton cancel = new JButton(translator.i18n("Cancel"));
	private Future<?> future = null;

	public ProgressPanel() {
		super();
		initUI();
	}

	private void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JPanel progressPanel = new JPanel();
		progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.Y_AXIS));
		progressPanel.add(description);
		progressPanel.add(text);
		progressPanel.add(progressBar);
		add(progressPanel);
		add(cancel);
		cancel.addActionListener(this);
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
	public void setDescription(String description) {
		this.description.setText(description);
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

	@Override
	public void finish() {
		if (future != null) {
			if (!future.isDone()) {
				future.cancel(true);
			}
			future = null;
		}
		setVisible(false);
		for (FinishListener listener : finishListeners) {
			listener.finished(this);
		}
	}

	public void addFinishListener(FinishListener listener) {
		finishListeners.add(listener);
	}

	public void removeFinishListener(FinishListener listener) {
		finishListeners.remove(listener);
	}

	@Override
	public ProgressObserver startSubProgress(ProgressObservable thread) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			finish();
		}
	}

	public void run(Runnable test) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		future = service.submit(test);
	}
}
