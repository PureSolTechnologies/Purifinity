package com.puresol.gui.progress;

import org.junit.Test;

public class ProgressWindowAppTest {

	private static class MainThread implements RunnableProgressObservable {

		private ProgressObserver observer = null;

		@Override
		public synchronized void run() {
			if (observer != null) {
				observer.setTitle("MainThread");
				observer.setRange(0, 9);
			}
			try {
				for (int i = 0; i < 10; i++) {
					if (observer != null) {
						observer.setStatus(i);
						observer.setText("Thread " + i);
						observer.showProgressPercent();
					}
					SubThread subThread = new SubThread();
					ProgressPanel panel = observer.getSubProgressPanel();
					panel.runSyncronous(subThread);
					if (Thread.interrupted() || panel.isTerminated()) {
						break;
					}
				}
			} catch (InterruptedException e) {
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

	private static class SubThread implements RunnableProgressObservable {

		private ProgressObserver observer = null;

		@Override
		public void run() {
			if (observer != null) {
				observer.setTitle("SubThread");
				observer.setRange(0, 9);
			}
			for (int i = 0; i < 10; i++) {
				if (observer != null) {
					observer.setStatus(i);
					observer.setText("Step " + i);
					observer.showProgressPercent();
				}
				try {
					Thread.sleep(20);
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

	@Test
	public void run() throws Exception {
		ProgressWindow progress = new ProgressWindow();
		MainThread thread = new MainThread();
		progress.runSynchronous(thread);
		progress.dispose();
	}
}
