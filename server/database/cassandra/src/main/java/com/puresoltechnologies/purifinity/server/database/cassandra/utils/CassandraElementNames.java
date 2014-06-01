package com.puresoltechnologies.purifinity.server.database.cassandra.utils;

import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;

public class CassandraElementNames {

	public static final String ANALYSIS_KEYSPACE = AnalysisStoreKeyspace.NAME;

	public static final String ANALYSIS_PROJECT_SETTINGS_TABLE = "project_settings";
	public static final String ANALYSIS_RUN_SETTINGS_TABLE = "run_settings";
	public static final String ANALYSIS_FILES_TABLE = "files";
	public static final String ANALYSIS_ANALYZES_TABLE = "analyzes";
	public static final String ANALYSIS_FILE_TREE_CACHE = "file_tree_cache";

	public static final String ANALYSIS_FILE_TREE_CACHE_TABLE = "file_tree_cache";

	public static final String EVALUATION_KEYSPACE = EvaluationStoreKeyspace.NAME;

	public static final String EVALUATION_FILES_TABLE = "files";
	public static final String EVALUATION_DIRECTORIES_TABLE = "directories";
	public static final String EVALUATION_PROJECTS_TABLE = "projects";
	public static final String EVALUATION_METRICS_TABLE = "metrics";

}
