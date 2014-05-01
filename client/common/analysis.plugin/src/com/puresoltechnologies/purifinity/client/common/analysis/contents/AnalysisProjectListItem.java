package com.puresoltechnologies.purifinity.client.common.analysis.contents;

import java.util.Date;
import java.util.UUID;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;

public class AnalysisProjectListItem {

	private final UUID uuid;
	private final Date creationTime;
	private final AnalysisProjectSettings settings;

	public AnalysisProjectListItem(UUID uuid, Date creationTime,
			AnalysisProjectSettings settings) {
		this.uuid = uuid;
		this.creationTime = creationTime;
		this.settings = settings;
	}

	public UUID getUUID() {
		return uuid;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public AnalysisProjectSettings getSettings() {
		return settings;
	}

}
