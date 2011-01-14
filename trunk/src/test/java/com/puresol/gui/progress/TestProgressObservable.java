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

import static org.junit.Assert.*;

import org.junit.Test;

public class TestProgressObservable implements RunnableProgressObservable {

	private ProgressObserver observer;
	private boolean startSubProcess = false;

	public TestProgressObservable() {

	}

	public void setStartSubProcess(boolean startSubProcess) {
		this.startSubProcess = startSubProcess;
	}

	@Override
	public void setMonitor(ProgressObserver observer) {
		this.observer = observer;
	}

	@Override
	public void run() {
		observer.setRange(0, 9);
		for (int i = 0; i < 10; i++) {
			observer.setStatus(i);
			if ((i == 5) && (startSubProcess)) {
				observer.startSubProgress(new TestProgressObservable());
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				observer.finish();
				return;
			}
			if (Thread.interrupted()) {
				observer.finish();
				return;
			}
		}
		observer.finish();
	}

	@Test
	public void testSuitability() {
		assertEquals(1, getClass().getConstructors().length);
	}
}
