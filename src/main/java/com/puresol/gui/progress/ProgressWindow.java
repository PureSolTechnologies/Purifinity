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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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

    private final ProgressPanel progressPanel = new ProgressPanel();
    private final Window frame;
    private boolean autoDispose = false;

    public ProgressWindow() {
	super("Progress Window");
	frame = null;
	initUI();
    }

    public ProgressWindow(boolean autoDispose) {
	super("Progress Window");
	frame = null;
	this.autoDispose = autoDispose;
	initUI();
    }

    public ProgressWindow(Window frame) {
	super("Progress Window");
	this.frame = frame;
	initUI();
    }

    public ProgressWindow(Window frame, boolean autoDispose) {
	super("Progress Window");
	this.frame = frame;
	this.autoDispose = autoDispose;
	initUI();
    }

    private void initUI() {
	JPanel panel = new JPanel();
	setContentPane(panel);
	panel.setLayout(new BorderLayout());
	panel.add(new JScrollPane(progressPanel), BorderLayout.CENTER);
	progressPanel.addFinishListener(this);

	setSize(640, 200);
	setLocationRelativeTo(frame);
	setVisible(true);
    }

    public boolean isAutoDispose() {
	return autoDispose;
    }

    public void setAutoDispose(boolean autoDispose) {
	this.autoDispose = autoDispose;
    }

    @Override
    public void terminated(ProgressObservable o) {
	remove((Component) o.getMonitor());
	for (FinishListener listener : finishListeners) {
	    listener.terminated(o);
	}
	autoDisposeIfSet();
    }

    @Override
    public void finished(ProgressObservable o) {
	remove((Component) o.getMonitor());
	for (FinishListener listener : finishListeners) {
	    listener.finished(o);
	}
	autoDisposeIfSet();
    }

    private void autoDisposeIfSet() {
	if (autoDispose) {
	    dispose();
	}
    }

    public void addFinishListener(FinishListener listener) {
	finishListeners.add(listener);
    }

    public void removeFinishListener(FinishListener listener) {
	finishListeners.remove(listener);
    }

    public void runAsynchronous(RunnableProgressObservable task) {
	progressPanel.runAsyncronous(task);
    }

    public void runAsynchronous(CallableProgressObservable<?> task) {
	progressPanel.runAsyncronous(task);
    }

    public void runSynchronous(RunnableProgressObservable task)
	    throws InterruptedException {
	progressPanel.runSyncronous(task);
    }

    public void runSynchronous(CallableProgressObservable<?> task)
	    throws InterruptedException {
	progressPanel.runSyncronous(task);
    }

}
