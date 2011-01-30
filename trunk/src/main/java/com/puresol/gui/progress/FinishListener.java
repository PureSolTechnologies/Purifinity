package com.puresol.gui.progress;

/**
 * This interface is used to information objects about finished processed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface FinishListener {

	/**
	 * This method is called if an observed process is finished.
	 * 
	 * @param o
	 *            is the process which is finished.
	 */
	public void finished(ProgressObservable o);

	public void terminated(ProgressObservable o);

}
