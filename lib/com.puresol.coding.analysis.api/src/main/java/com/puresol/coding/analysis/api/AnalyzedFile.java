package com.puresol.coding.analysis.api;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import com.puresol.utils.HashId;

/**
 * This class is for keeping a list of analyzed files within ProjectAnalyzer.
 * This class provides easy and standardized access to the workspace
 * directories.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalyzedFile implements Comparable<AnalyzedFile>, Serializable {

    private static final long serialVersionUID = 2030120585873480183L;

    private final HashId hashId;
    private final File file;
    private final Date time;
    private final long timeOfRun;
    private final ProgrammingLanguage language;

    public AnalyzedFile(HashId hashId, File file, Date time, long timeOfRun,
	    ProgrammingLanguage language) {
	super();
	this.hashId = hashId;
	this.file = file;
	this.time = time;
	this.timeOfRun = timeOfRun;
	this.language = language;
    }

    public HashId getHashId() {
	return hashId;
    }

    public File getFile() {
	return file;
    }

    public Date getTime() {
	return time;
    }

    public long getTimeOfRun() {
	return timeOfRun;
    }

    public ProgrammingLanguage getLanguage() {
	return language;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((file == null) ? 0 : file.hashCode());
	result = prime * result + ((hashId == null) ? 0 : hashId.hashCode());
	result = prime * result
		+ ((language == null) ? 0 : language.hashCode());
	result = prime * result + ((time == null) ? 0 : time.hashCode());
	result = prime * result + (int) (timeOfRun ^ (timeOfRun >>> 32));
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	AnalyzedFile other = (AnalyzedFile) obj;
	if (file == null) {
	    if (other.file != null)
		return false;
	} else if (!file.equals(other.file))
	    return false;
	if (hashId == null) {
	    if (other.hashId != null)
		return false;
	} else if (!hashId.equals(other.hashId))
	    return false;
	if (language == null) {
	    if (other.language != null)
		return false;
	} else if (!language.equals(other.language))
	    return false;
	if (time == null) {
	    if (other.time != null)
		return false;
	} else if (!time.equals(other.time))
	    return false;
	if (timeOfRun != other.timeOfRun)
	    return false;
	return true;
    }

    @Override
    public int compareTo(AnalyzedFile other) {
	return this.file.compareTo(other.file);
    }
}
