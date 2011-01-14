package com.puresol.gui.progress;

/**
 * This interface is implemented in all classes which need to be notified by a
 * finished thread.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface FinishListener {

	public void finished(Runnable runnable);

}
