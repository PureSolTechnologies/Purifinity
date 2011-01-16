package com.puresol.gui.progress;

import java.util.concurrent.Callable;

public interface CallableProgressObservable<T> extends Callable<T>,
		ProgressObservable {

}
