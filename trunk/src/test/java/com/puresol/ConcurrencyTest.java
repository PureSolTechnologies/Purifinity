package com.puresol;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

import org.junit.Test;

public class ConcurrencyTest {

	private class ProgressWindow extends JWindow {

		private static final long serialVersionUID = -2089799204409905907L;

		JProgressBar progressBar = new JProgressBar();

		public ProgressWindow(int min, int max) {
			super();
			progressBar.setMinimum(min);
			progressBar.setMaximum(max);
			this.setLocationRelativeTo(null);

			setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
			add(new JLabel("Test"));
			add(progressBar);
			progressBar.setStringPainted(true);
			pack();
		}

		public void set(int value) {
			progressBar.setValue(value);
		}

	}

	private class TestClass implements Callable<Integer> {

		private final int steps;
		private final ProgressWindow progressWindow;

		public TestClass(int steps, ProgressWindow progressWindow) {
			super();
			this.steps = steps;
			this.progressWindow = progressWindow;
		}

		@Override
		public Integer call() throws Exception {
			for (int i = 0; i < steps; i++) {
				System.out.println("Step " + i + "...");
				Thread.sleep(new Random().nextInt(10));
				progressWindow.set(i);
			}
			System.out.println("done.");
			return steps;
		}

	}

	@Test
	public void test() {
		ProgressWindow progressWindow = new ProgressWindow(0, 100);
		try {
			progressWindow.setVisible(true);
			ExecutorService service = Executors.newSingleThreadExecutor();
			service.submit(new TestClass(100, progressWindow));
			try {
				service.awaitTermination(3, TimeUnit.SECONDS);
			} finally {
				service.shutdown();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} finally {
			progressWindow.setVisible(false);
		}
	}
}
