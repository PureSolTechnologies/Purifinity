package com.puresoltechnologies.purifinity.server.database.hadoop.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

/**
 * This class provides some common functionality to deal with Hadoop.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public class HadoopClientHelper {

    public static final String PURIFINITY_DIRECTORY = "/apps/Purifinity";

    /**
     * This method provides the default configuration for Hadoop client.
     * 
     * @return
     */
    public static Configuration createConfiguration() {
	Configuration hadoopConfiguration = new Configuration();
	hadoopConfiguration.addResource(new Path("/opt/hadoop/etc/hadoop/core-site.xml"));
	hadoopConfiguration.addResource(new Path("/opt/hadoop/etc/hadoop/hdfs-site.xml"));
	hadoopConfiguration.addResource(new Path("/opt/hadoop/etc/hadoop/mapred-site.xml"));
	return hadoopConfiguration;
    }

    public static String getFilesPath() {
	return PURIFINITY_DIRECTORY + "/files";
    }

    public static String getProjectsPath() {
	return PURIFINITY_DIRECTORY + "/projects";
    }

    public static String getProjectPath(String projectId) {
	return getProjectsPath() + "/" + projectId;
    }

    public static String getProjectRunPath(String projectId, long runId) {
	return getProjectPath(projectId) + "/runs/" + runId;
    }

    public static String getPreAnalysisScriptStdoutFile(String projectId, long runId) {
	return getProjectRunPath(projectId, runId) + "/preAnalysisScript.stdout";
    }
}
