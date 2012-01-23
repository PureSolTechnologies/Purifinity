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
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.utils.Persistence;

abstract public class AbstractAnalyser implements Analyzer {

    private static final long serialVersionUID = -2593701440766091118L;

    private static final Logger logger = LoggerFactory
	    .getLogger(AbstractAnalyser.class);

    private final File file;

    private final Date timeStamp;

    public AbstractAnalyser(File file) {
	this.file = file;
	timeStamp = new Date();
    }

    @Override
    public Date getTimeStamp() {
	return timeStamp;
    }

    @Override
    public File getFile() {
	return file;
    }

    @Override
    public boolean persist(File file) {
	try {
	    File persistDirectory = file.getParentFile();
	    if (!persistDirectory.exists()) {
		if (!persistDirectory.mkdirs()) {
		    return false;
		}
	    }
	    Persistence.persist(this, file);
	    return true;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    return false;
	}
    }

}
