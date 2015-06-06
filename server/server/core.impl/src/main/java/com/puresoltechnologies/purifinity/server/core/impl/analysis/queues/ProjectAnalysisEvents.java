package com.puresoltechnologies.purifinity.server.core.impl.analysis.queues;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.server.systemmonitor.core.api.events.Event;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventSeverity;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventType;

/**
 * This class produces messages for the project analysis process.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ProjectAnalysisEvents {

	private static final String COMPONENT_NAME = "ProjectAnalysisProcess";

	public static Event createGeneralError(Throwable t) {
		Event event = new Event(COMPONENT_NAME, 0x00,
				EventType.SYSTEM_EXCEPTION, EventSeverity.INFO,
				"Project could not be analyzed due to error.");
		event.setThrowable(t);
		return event;
	}

	public static Event createQueueAnalysisEvent(String projectId, String name) {
		return new Event(COMPONENT_NAME, 0x01, EventType.SYSTEM,
				EventSeverity.INFO, "Project '" + name + "' (" + projectId
						+ ") is going to be queued for analysis.");
	}

	public static Event createRawFileStoredEvent(String internalLocation,
			HashId hashId, String projectName, String repositoryString) {
		return new Event(COMPONENT_NAME, 0x02, EventType.SYSTEM,
				EventSeverity.INFO, "File '" + internalLocation
						+ "' was stored with hash id '" + hashId.toString()
						+ "' (project: '" + projectName + "', repository: '"
						+ repositoryString + "').");
	}

	public static Event createRawFileStoreError(String internalLocation,
			String projectName, String repositoryString, Throwable t) {
		Event event = new Event(COMPONENT_NAME, 0x03, EventType.SYSTEM,
				EventSeverity.INFO, "File '" + internalLocation
						+ "' could not be stored (project: '" + projectName
						+ "', repository: '" + repositoryString + "').");
		event.setThrowable(t);
		return event;
	}

	public static Event createRawFileNotStoredEvent(String internalLocation,
			String projectName, String repositoryString) {
		return new Event(COMPONENT_NAME, 0x04, EventType.SYSTEM,
				EventSeverity.INFO, "File '" + internalLocation
						+ "' was not stored because of filtering. (project: '"
						+ projectName + "', repository: '" + repositoryString
						+ "').");
	}

	/**
	 * A private default constructor to avoid instantiation.
	 */
	private ProjectAnalysisEvents() {
	}

}
