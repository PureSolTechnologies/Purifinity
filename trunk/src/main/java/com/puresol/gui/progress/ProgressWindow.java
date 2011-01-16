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

import javax.swing.JFrame;

import com.puresol.ListenerSet;

/**
 * This class is a simple implementation for a splash screen for application
 * start ups.
 * 
 * @author Rick-Rainer ludwig
 * 
 */
public class ProgressWindow extends JFrame implements ProgressObserver,
		FinishListener {

	private static final long serialVersionUID = 4191554073727049318L;

	private final ListenerSet<FinishListener> finishListeners = new ListenerSet<FinishListener>();

	private final ProgressPanel progressPanel = new ProgressPanel();

	private final JFrame frame;

	public ProgressWindow() {
		super();
		frame = null;
		initUI();
	}

	public ProgressWindow(JFrame frame) {
		super();
		this.frame = frame;
		initUI();
	}

	private void initUI() {
		getContentPane().add(progressPanel);
		setLocationRelativeTo(frame);
		setVisible(true);
		pack();
		progressPanel.addFinishListener(this);
	}

	@Override
	public void setText(String text) {
		progressPanel.setText(text);
	}

	@Override
	public void setRange(int min, int max) {
		progressPanel.setRange(min, max);
	}

	@Override
	public void setStatus(int value) {
		progressPanel.setStatus(value);
	}

	@Override
	public void setDescription(String description) {
		progressPanel.setDescription(description);
	}

	@Override
	public void setProgressText(String text) {
		progressPanel.setProgressText(text);
	}

	@Override
	public void showProgressPercent() {
		progressPanel.showProgressPercent();
	}

	@Override
	public void finish() {
		for (FinishListener listener : finishListeners) {
			listener.finished(progressPanel.getTask());
		}
		dispose();
	}

	@Override
	public ProgressObserver startSubProgress(ProgressObservable thread) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void finished(Object o) {
		finish();
	}

	public void addFinishListener(FinishListener listener) {
		finishListeners.add(listener);
	}

	public void removeFinishListener(FinishListener listener) {
		finishListeners.remove(listener);
	}

	public void run(RunnableProgressObservable task) {
		task.setMonitor(this);
		progressPanel.run(task);
	}

	public void run(CallableProgressObservable<?> task) {
		task.setMonitor(this);
		progressPanel.run(task);
	}
}
