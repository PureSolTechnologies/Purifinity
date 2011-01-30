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
	public void test() {
		ProgressWindow observer = new ProgressWindow();
		TestThread test = new TestThread();
		observer.run(test);
		System.out.println("done.");
	}

	private static class MainThread implements RunnableProgressObservable,
			FinishListener {

		private ProgressObserver observer = null;
		private boolean interrupted = false;

		@Override
		public void run() {
			if (observer != null) {
				observer.setDescription("MainThread");
				observer.setRange(0, 9);
			}
			// try {
			for (int i = 0; i < 10; i++) {
				if (observer != null) {
					observer.setStatus(i);
					observer.setText("Thread " + i);
				}
				SubThread subThread = new SubThread();
				observer.runSubProcess(subThread);
				if (Thread.interrupted() || interrupted) {
					break;
				}
			}
			// } catch (InterruptedException e) {
			// }
			if (observer != null) {
				observer.finished(this);
			}
		}

		@Override
		public void setMonitor(ProgressObserver observer) {
			this.observer = observer;
		}

		@Override
		public ProgressObserver getMonitor() {
			return observer;
		}

		@Override
		public void finished(ProgressObservable o) {
		}

		@Override
		public void terminated(ProgressObservable o) {
		}

	}

	private static class SubThread implements RunnableProgressObservable {

		private ProgressObserver observer = null;

		@Override
		public void run() {
			if (observer != null) {
				observer.setDescription("SubThread");
				observer.setRange(0, 9);
			}
			for (int i = 0; i < 10; i++) {
				if (observer != null) {
					observer.setStatus(i);
					observer.setText("Step " + i);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					break;
				}
				if (Thread.interrupted()) {
					break;
				}
			}
			if (observer != null) {
				observer.finished(this);
			}
		}

		@Override
		public void setMonitor(ProgressObserver observer) {
			this.observer = observer;
		}

		@Override
		public ProgressObserver getMonitor() {
			return observer;
		}

	}

	public static void main(String args[]) {
		ProgressWindow progress = new ProgressWindow();
		MainThread mainThread = new MainThread();
		progress.run(mainThread);
		progress.dispose();
	}

}
