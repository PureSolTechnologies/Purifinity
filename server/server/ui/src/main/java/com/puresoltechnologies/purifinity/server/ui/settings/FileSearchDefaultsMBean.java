package com.puresoltechnologies.purifinity.server.ui.settings;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ApplicationScoped
@ManagedBean
public class FileSearchDefaultsMBean {

	private String directoryIncludes = "";
	private String directoryExcludes = "";
	private String fileIncludes = "";
	private String fileExcludes = "";
	private boolean ignoreHidden = true;

	public String getDirectoryIncludes() {
		return directoryIncludes;
	}

	public void setDirectoryIncludes(String directoryIncludes) {
		this.directoryIncludes = directoryIncludes;
	}

	public String getDirectoryExcludes() {
		return directoryExcludes;
	}

	public void setDirectoryExcludes(String directoryExcludes) {
		this.directoryExcludes = directoryExcludes;
	}

	public String getFileIncludes() {
		return fileIncludes;
	}

	public void setFileIncludes(String fileIncludes) {
		this.fileIncludes = fileIncludes;
	}

	public String getFileExcludes() {
		return fileExcludes;
	}

	public void setFileExcludes(String fileExcludes) {
		this.fileExcludes = fileExcludes;
	}

	public boolean isIgnoreHidden() {
		return ignoreHidden;
	}

	public void setIgnoreHidden(boolean ignoreHidden) {
		this.ignoreHidden = ignoreHidden;
	}

}
