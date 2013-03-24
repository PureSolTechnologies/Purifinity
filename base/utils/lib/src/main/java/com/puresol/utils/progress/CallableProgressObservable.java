package com.puresol.utils.progress;

import java.util.concurrent.Callable;

public interface CallableProgressObservable<Observable, Return> extends
	Callable<Return>, ProgressObservable<Observable> {

}