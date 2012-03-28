package com.puresol.coding.metrics;

import java.util.List;
import java.util.concurrent.Callable;

import com.puresol.coding.evaluator.Result;
import com.puresol.progress.ProgressObservable;

/**
 * This is an interface for a general metric. This metric is not used due to it
 * is not known which kind of metric it is. It is only specified that this
 * metric has listeners, is callable and has a result list to return.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Metric extends Callable<Void>, ProgressObservable {

    @Override
    public Void call();

    /**
     * This method returns the results of this evaluator as plain generic list.
     * 
     * @return A list of type Result is returned.
     */
    public List<Result> getResults();

}
