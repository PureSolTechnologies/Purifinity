package com.puresoltechnologies.purifinity.server.database.hadoop;

import java.io.IOException;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.slf4j.Logger;

public class HdfsFileSystemProducter {

    @Inject
    private Logger logger;

    @Produces
    public FileSystem getAnalysisSession() {
	try {
	    Configuration configuration = HadoopClientHelper.createConfiguration();
	    FileSystem fileSystem = FileSystem.newInstance(configuration);
	    return fileSystem;
	} catch (IOException e) {
	    throw new RuntimeException("Could not create new instance for Hadoop file system.", e);
	}
    }

    public void closeAnalysisKeyspaceSession(@Disposes FileSystem session) {
	try {
	    session.close();
	} catch (IOException e) {
	    logger.warn("Could not close Hadoop file system.", e);
	}
    }

}
