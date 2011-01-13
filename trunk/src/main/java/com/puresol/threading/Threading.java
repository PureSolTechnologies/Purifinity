package com.puresol.threading;

import java.util.List;

import javax.swingx.progress.RunnableProgressObservable;
import javax.swingx.progress.ProgressObserver;

public class Threading implements RunnableProgressObservable {

	public static int getAvailableProcessors() {
		return Runtime.getRuntime().availableProcessors();
	}

	private final int maxThreads;
	private final List<Thread> threads;
	private ProgressObserver progressObserver = null;

	public Threading(int maxThreads, List<Thread> threads) {
		if ((maxThreads < 0) || (maxThreads > 10 * getAvailableProcessors())) {
			throw new IllegalArgumentException(
					"The number of threads has to be a number of minimum one"
							+ " and maximum 10 times the number of available"
							+ " processors.!");
		}
		this.maxThreads = maxThreads;
		this.threads = threads;
	}

	@Override
	public void setMonitor(ProgressObserver observer) {
		this.progressObserver = observer;
	}

	@Override
	public void run() {
		int running = 0;
		for (Thread thread : threads) {
			running++;
			if (RunnableProgressObservable.class.isAssignableFrom(thread.getClass())) {
				progressObserver.startSubProgress(RunnableProgressObservable.class
						.cast(thread));
			} else {
				thread.run();
			}
			if (running >= maxThreads) {
				// TODO
			}
		}
	}
}
