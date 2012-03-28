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
 * This interfaces is used for classes which are to be monitored.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ProgressObservable {

    /**
     * This method adds a new progress listener.
     * 
     * @param progressListener
     *            is the listener object to be added.
     */
    public void addProgressListener(ProgressListener progressListener);

    /**
     * This method removes a former added progress listener.
     * 
     * @param progressListener
     *            is the object to be added as listener.
     */
    public void removeProgressListener(ProgressListener progressListener);
}
