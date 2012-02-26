package com.puresol.uhura.ust;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.puresol.uhura.ust.facilities.CompilerRelevantElement;

public class CompilationUnit extends CompilerRelevantElement {

    private static final long serialVersionUID = -5790049234290910253L;

    private final List<UniversalSyntaxTree> children = new ArrayList<UniversalSyntaxTree>();
    /**
     * This file keeps the file where the compilation unit was found.
     */
    private final File file;
    private final String language;
    private final String version;

    public CompilationUnit(File file, String language, String version) {
	super("");
	this.file = file;
	this.language = language;
	this.version = version;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
	return "Compilation Unit";
    }

    public File getFile() {
	return file;
    }

    public String getLanguage() {
	return language;
    }

    public String getVersion() {
	return version;
    }

    public void addChild(UniversalSyntaxTree child) {
	children.add(child);
	child.setParent(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean hasChildren() {
	return children.size() > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<UniversalSyntaxTree> getChildren() {
	return children;
    }

}
