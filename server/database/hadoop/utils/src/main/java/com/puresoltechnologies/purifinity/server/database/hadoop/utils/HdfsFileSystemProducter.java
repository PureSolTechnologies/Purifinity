package com.puresoltechnologies.purifinity.server.database.hadoop.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.HdfsConstants;
import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.common.utils.annotation.Workaround;

public class HdfsFileSystemProducter {

    @Inject
    private Logger logger;

    @Produces
    @Singleton
    @Workaround(message = "The service registration needs to be removed and replaced by correct service loading in WildFly/JBoss.")
    public FileSystem getAnalysisSession() {
	try {
	    logger.info("Produce Hadoop Filesystem...");

	    Method loadFileSystemsMethod = FileSystem.class.getDeclaredMethod("loadFileSystems");
	    loadFileSystemsMethod.setAccessible(true);
	    loadFileSystemsMethod.invoke(null);
	    Field serviceFileSystemsField = FileSystem.class.getDeclaredField("SERVICE_FILE_SYSTEMS");
	    serviceFileSystemsField.setAccessible(true);
	    @SuppressWarnings("unchecked")
	    Map<String, Class<? extends FileSystem>> fileSystems = (Map<String, Class<? extends FileSystem>>) serviceFileSystemsField
		    .get(null);
	    fileSystems.put(HdfsConstants.HDFS_URI_SCHEME, DistributedFileSystem.class);
	    fileSystems.put("file", LocalFileSystem.class);
	    Configuration configuration = HadoopClientHelper.createConfiguration();
	    FileSystem fileSystem = FileSystem.newInstance(configuration);
	    logger.info("Hadoop Filesystem produced.");
	    return fileSystem;
	} catch (IOException e) {
	    throw new RuntimeException("Could not create new instance for Hadoop file system.", e);
	} catch (NoSuchMethodException | NoSuchFieldException | SecurityException | IllegalAccessException
		| IllegalArgumentException | InvocationTargetException e) {
	    throw new RuntimeException(
		    "Could not create new instance for Hadoop file system due registration of HDFS FileSystem errored.",
		    e);
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
