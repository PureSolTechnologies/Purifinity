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

package com.puresol.log;

import java.util.Vector;

import org.apache.log4j.AsyncAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.TTCCLayout;
import org.apache.log4j.helpers.ISO8601DateFormat;
import org.apache.log4j.spi.LoggingEvent;

import com.puresol.gui.log.LogViewer;

/**
 * This appender is used to send logging information to the LoggingDialog.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LogViewerAppender extends AsyncAppender {

	// TODO check weigher a this logger or the RootLogger should be used!
	private static final Logger logger = Logger
			.getLogger(LogViewerAppender.class);
	private static LogViewerAppender instance = null;

	private Vector<LogViewer> viewers = null;

	static public LogViewerAppender getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	static private synchronized void createInstance() {
		if (instance == null) {
			instance = new LogViewerAppender();
		}
	}

	private LogViewerAppender() {
		super();
		viewers = new Vector<LogViewer>();
		this.setLayout(new TTCCLayout(ISO8601DateFormat.ISO8601_DATE_FORMAT));
	}

	public void addViewer(LogViewer viewer) {
		viewers.add(viewer);
		if (!logger.isAttached(this)) {
			logger.addAppender(this);
		}
	}

	public void removeViewer(LogViewer viewer) {
		viewers.remove(viewer);
		if (viewers.size() == 0) {
			logger.removeAppender(this);
		}
	}

	public void append(LoggingEvent event) {
		super.append(event);
		String message = getLayout().format(event);
		for (int index = 0; index < viewers.size(); index++) {
			viewers.get(index).addLog(message);
		}
	}
}
