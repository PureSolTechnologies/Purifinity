package com.puresoltechnologies.purifinity.server.database.hbase;

public class HBaseElementNames {

    public static final String ANALYSIS_PROJECT_SETTINGS_TABLE = "analysis_service.project_settings";
    public static final String ANALYSIS_RUN_SETTINGS_TABLE = "analysis_service.run_settings";
    public static final String ANALYSIS_ANALYSES_TABLE = "analysis_service.analyses";

    public static final String SYSTEM_PREFERENCES_TABLE = "preferences_store.system_preferences";
    public static final String USER_PREFERENCES_TABLE = "preferences_store.user_preferences";
    public static final String USER_DEFAULTS_PREFERENCES_TABLE = "preferences_store.user_default_preferences";
    public static final String PLUGIN_PREFERENCES_TABLE = "preferences_store.plugin_preferences";
    public static final String PLUGIN_DEFAULTS_PREFERENCES_TABLE = "preferences_store.plugin_default_preferences";
    public static final String SERVICE_ACTIVATION_TABLE = "preferences_store.service_activation";
    public static final String SERVICE_PROJECT_ACTIVATION_TABLE = "preferences_store.service_project_activation";

    public static final String EVALUATION_METRICS_TABLE = "evaluator_store.metrics";

    public static final String EVALUATION_PARAMETERS_TABLE = "evaluator_store.parameters";
    public static final String EVALUATION_FILE_METRICS_TABLE = "evaluator_store.file_metrics";
    public static final String EVALUATION_DIRECTORY_METRICS_TABLE = "evaluator_store.directory_metrics";
    public static final String EVALUATION_PROJECT_METRICS_TABLE = "evaluator_store.project_metrics";

}
