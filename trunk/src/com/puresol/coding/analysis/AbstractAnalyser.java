/***************************************************************************
 *
 *   AbstractAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import java.io.File;
import java.util.ArrayList;

import com.puresol.coding.CodeRange;

abstract public class AbstractAnalyser implements Analyser {

    private File projectDirectory = null;
    private File file = null;

    private ArrayList<CodeRange> codeRanges = new ArrayList<CodeRange>();

    public AbstractAnalyser(File projectDirectory, File file) {
	this.projectDirectory = projectDirectory;
	this.file = file;
    }

    public File getProjectDirectory() {
	return projectDirectory;
    }

    public File getFile() {
	return file;
    }

    protected void addCodeRanges(ArrayList<CodeRange> codeRanges) {
	this.codeRanges.addAll(codeRanges);
    }

    public ArrayList<CodeRange> getCodeRanges() {
	return codeRanges;
    }
}
