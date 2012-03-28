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

package com.puresol.progress;

/**
 * This interface is implemented into all classes which are used to observe a
 * progress of another thread or process.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ProgressListener {

    /**
     * This method sets some description output for the currently running
     * process and the total amount of work.
     * 
     * @param text
     *            is the text to be presented in the progress status.
     * @param totalAmountOfWork
     *            is a measurable number of work. This is a arbitrary number
     *            which needs to be provided to calculate a progress bar.
     */
    public void init(String title, int totalAmountOfWork);

    /**
     * This method sets the value for the status bar.
     * 
     * @param amountOfWork
     *            is the amount of work process since the last call to this
     *            method.
     */
    public void worked(int amountOfWork);

}
