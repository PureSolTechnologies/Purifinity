/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.gui.progress;

/**
 * This interface is implemented into all classes which are used to observe a
 * progress of another thread or process.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ProgressObserver {

	/**
	 * This method sets some description output for the currently running
	 * process.
	 * 
	 * @param description
	 */
	public void setDescription(String description);

	/**
	 * This method sets the range for the progress bar.
	 * 
	 * @param min
	 * @param max
	 */
	public void setRange(int min, int max);

	/**
	 * This method sets the value for the status bar.
	 * 
	 * @param status
	 */
	public void setStatus(int status);

	/**
	 * This method sets the text for a dynamic text outside the progress bar.
	 * 
	 * @param text
	 */
	public void setText(String text);

	/**
	 * This method sets the text within the progress bar.
	 * 
	 * @param text
	 */
	public void setProgressText(String text);

	/**
	 * This methods makes the observer showing the percentage within the
	 * progress bar.
	 */
	public void showProgressPercent();

	/**
	 * This method is called when the process is finished to finish the progress
	 * bar and to trigger post-process activities of the observer.
	 * 
	 * @param task
	 *            is the task object which was finished.
	 */
	public void finished(ProgressObservable o);

	public void terminated(ProgressObservable o);

	public void runSubProcess(RunnableProgressObservable task);

	public void runSubProcess(CallableProgressObservable<?> task);

}
