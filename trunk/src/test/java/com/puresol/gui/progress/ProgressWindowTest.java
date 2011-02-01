package com.puresol.gui.progress;

import java.util.Random;

import org.junit.Test;

public class ProgressWindowTest {

	class TestThread implements RunnableProgressObservable {

		public ProgressObserver observer;

		@Override
		public void setMonitor(ProgressObserver observer) {
			this.observer = observer;
		}

		@Override
		public ProgressObserver getMonitor() {
			return observer;
		}

		@Override
		public void run() {
			observer.setDescription("Test Description");
			observer.setText("Text");
			observer.setRange(0, 100);
			for (int i = 0; i <= 100; i++) {
				observer.setStatus(i);
				try {
					Thread.sleep(new Random().nextInt(10));
				} catch (InterruptedException e) {
					return;
				}
			}
			observer.finished(this);
		}

	}

	@Test
	public void test() throws Exception {
		ProgressWindow observer = new ProgressWindow(true);
		TestThread test = new TestThread();
		observer.runSynchronous(test);
		System.out.println("done.");
	}
}
