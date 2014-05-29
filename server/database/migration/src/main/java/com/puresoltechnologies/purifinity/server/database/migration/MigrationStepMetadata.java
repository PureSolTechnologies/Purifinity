package com.puresoltechnologies.purifinity.server.database.migration;

import com.puresoltechnologies.commons.misc.Version;

public class MigrationStepMetadata {

	private Version version;
	private String developer;
	private String keyspace;
	private String command;
	private String comment;

	public MigrationStepMetadata(Version version, String developer,
			String keyspace, String command, String comment) {
		super();
		this.version = version;
		this.developer = developer;
		this.keyspace = keyspace;
		this.command = command;
		this.comment = comment;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getKeyspace() {
		return keyspace;
	}

	public void setKeyspace(String keyspace) {
		this.keyspace = keyspace;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
