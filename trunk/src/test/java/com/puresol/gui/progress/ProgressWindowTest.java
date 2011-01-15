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
		public void run() {
			observer.setDescription("Test Description");
			observer.setText("Text");
			observer.setRange(0, 100);
			for (int i = 0; i <= 100; i++) {
				observer.setStatus(i);
				try {
					Thread.sleep(new Random().nextInt(100));
				} catch (InterruptedException e) {
					return;
				}
			}
			observer.finish();
		}

	}

	@Test
	public void test() {
		ProgressWindow observer = new ProgressWindow();
		TestThread test = new TestThread();
		observer.run(test);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("done.");
	}

}
