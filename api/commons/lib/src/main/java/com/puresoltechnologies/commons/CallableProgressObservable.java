package com.puresoltechnologies.commons;

import java.util.concurrent.Callable;

public interface CallableProgressObservable<Observable, Return> extends
	Callable<Return>, ProgressObservable<Observable> {

}
