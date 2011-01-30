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

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import com.puresol.ListenerSet;

/**
 * This class is a simple implementation for a splash screen for application
 * start ups.
 * 
 * @author Rick-Rainer ludwig
 * 
 */
public class ProgressWindow extends JFrame implements FinishListener {

	private static final long serialVersionUID = 4191554073727049318L;

	private final ListenerSet<FinishListener> finishListeners = new ListenerSet<FinishListener>();

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
		getContentPane().setLayout(
				new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setLocationRelativeTo(frame);
		setVisible(true);
		pack();
	}

	@Override
	public void terminated(ProgressObservable o) {
		if (o != null) {
			remove((Component) o.getMonitor());
			for (FinishListener listener : finishListeners) {
				listener.terminated(o);
			}
		}
	}

	@Override
	public void finished(ProgressObservable o) {
		if (o != null) {
			remove((Component) o.getMonitor());
			for (FinishListener listener : finishListeners) {
				listener.finished(o);
			}
		}
	}

	public void addFinishListener(FinishListener listener) {
		finishListeners.add(listener);
	}

	public void removeFinishListener(FinishListener listener) {
		finishListeners.remove(listener);
	}

	public void run(RunnableProgressObservable task) {
		ProgressPanel progressPanel = new ProgressPanel();
		progressPanel.addFinishListener(this);
		add(progressPanel);
		progressPanel.run(task);
	}

	public void run(CallableProgressObservable<?> task) {
		ProgressPanel progressPanel = new ProgressPanel();
		progressPanel.addFinishListener(this);
		add(progressPanel);
		progressPanel.run(task);
	}

}
