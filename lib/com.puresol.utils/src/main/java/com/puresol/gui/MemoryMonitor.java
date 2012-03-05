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

package com.puresol.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This element is a typical Label, but contains memory usage information.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class MemoryMonitor extends JLabel implements Runnable, ActionListener {

    private static final long serialVersionUID = 4484584488099486969L;

    private static final Logger logger = LoggerFactory
	    .getLogger(MemoryMonitor.class);
    private static final Runtime runtime = Runtime.getRuntime();

    private static int millisecondsDelay = 1000;
    private final boolean swingTimer;
    private Timer timer = null;
    private Thread thread = null;

    public MemoryMonitor() {
	super();
	this.swingTimer = false;
	init();
    }

    public MemoryMonitor(boolean swingTimer) {
	super();
	this.swingTimer = swingTimer;
	init();
    }

    private void init() {
	if (swingTimer) {
	    logger.info("Start memory monitor with Swing timer...");
	    timer = new Timer(1000, this);
	    timer.start();
	} else {
	    logger.info("Start memory monitor with Application managed thread...");
	    thread = Application.getInstance().getThread(this);
	    thread.start();
	}
    }

    public static int getMillisecondsDelay() {
	return millisecondsDelay;
    }

    public static void setMillisecondsDelay(int millisecondsDelay) {
	MemoryMonitor.millisecondsDelay = millisecondsDelay;
    }

    public static String getMemoryStatus() {
	double max = runtime.maxMemory() / 1024.0 / 1024.0;
	double total = runtime.totalMemory() / 1024.0 / 1024.0;
	double free = runtime.freeMemory() / 1024.0 / 1024.0;
	double usage = total / max * 100.0;
	max = Math.round(max * 100.0) / 100.0;
	total = Math.round(total * 100.0) / 100.0;
	free = Math.round(free * 100.0) / 100.0;
	usage = Math.round(usage * 100.0) / 100.0;
	return "max: " + max + "MB, total: " + total + "MB, free: " + free
		+ "MB (usage: " + usage + "%)";
    }

    private void setText() {
	setText(getMemoryStatus());
    }

    @Override
    public void run() {
	try {
	    while (true) {
		setText();
		Thread.sleep(millisecondsDelay);
	    }
	} catch (InterruptedException e) {
	    logger.info("Memory monitor interrupted.");
	}
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	setText();
	if (timer.getDelay() != millisecondsDelay) {
	    timer.setDelay(millisecondsDelay);
	}
    }
}
