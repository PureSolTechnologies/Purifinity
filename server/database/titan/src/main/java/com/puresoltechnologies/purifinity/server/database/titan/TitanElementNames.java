package com.puresoltechnologies.purifinity.server.database.titan;

public class TitanElementNames {

    /*
     * ANALYSIS PROJECTS
     */
    public static final String ANALYSIS_PROJECT_UUID_PROPERTY = "analysis.project.uuid";
    public static final String ANALYSIS_PROJECT_NAME_PROPERTY = "analysis.project.name";
    public static final String ANALYSIS_PROJECT_DESCRIPTION_PROPERTY = "analysis.project.description";
    /*
     * ANALYSIS RUNS
     */
    public static final String ANALYSIS_RUN_UUID_PROPERTY = "analysis.run.uuid";
    public static final String ANALYSIS_RUN_START_TIME_PROPERTY = "analysis.run.time.start";
    public static final String ANALYSIS_RUN_DURATION_PROPERTY = "analysis.run.time.duration";
    public static final String ANALYSIS_RUN_DESCRIPTION_PROPERTY = "analysis.run.description";
    public static final String ANALYZED_CONTENT_TREE_LABEL = "analyzedContentTree";
    public static final String ANALYZED_FILE_TREE_LABEL = "analyzedFileTree";
    public static final String HAS_CONTENT_LABEL = "hasContent";

    /*
     * FILE TREE
     */
    public static final String TREE_ELEMENT_NAME = "tree.element.name";
    public static final String TREE_ELEMENT_HASH = "tree.element.hashid";
    public static final String TREE_FS_ELEMENT_HASH = "tree.fs.element.hashid";
    public static final String TREE_ELEMENT_CONTAINS_FILES = "tree.element.contains.files";
    public static final String TREE_ELEMENT_CONTAINS_DIRECTORIES = "tree.element.contains.directories";
    public static final String TREE_ELEMENT_CONTAINS_FILES_RECURSIVE = "tree.element.contains.files.recursive";
    public static final String TREE_ELEMENT_CONTAINS_DIRECTORIES_RECURSIVE = "tree.element.contains.directories.recursive";
    public static final String TREE_ELEMENT_SIZE = "tree.element.size";
    public static final String TREE_ELEMENT_SIZE_RECURSIVE = "tree.element.size.recursive";
    public static final String TREE_ELEMENT_SOURCE_CODE_LOCATION = "tree.element.source.location";
    public static final String CONTAINS_FILE_LABEL = "containsFile";
    public static final String CONTAINS_DIRECTORY_LABEL = "containsDirectory";
    /*
     * ANALYSIS
     */
    public static final String ANALYSIS_NAME_PROPERTY = "analysis.name";
    public static final String ANALYSIS_VERSION_PROPERTY = "analysis.version";
    public static final String ANALYSIS_START_TIME_PROPERTY = "analysis.time.start";
    public static final String ANALYSIS_DURATION_PROPERTY = "analysis.time.duration";

    public static final String ANALYSIS_LANGUAGE_NAME_PROPERTY = "analysis.language.name";
    public static final String ANALYSIS_LANGUAGE_VERSION_PROPERTY = "analysis.language.version";
    public static final String ANALYSIS_SUCCESSFUL_PROPERTY = "analysis.successful";
    public static final String ANALYSIS_MESSAGE_PROPERTY = "analysis.message";
    /*
     * GENERAL PURPOSE
     */
    public static final String CREATION_TIME_PROPERTY = "time.creation";
    public static final String HAS_ANALYSIS_RUN_LABEL = "has_run";
    /*
     * User Management
     */
    public static final String USER_EMAIL_PROPERTY = "user.email";
    public static final String USER_NAME_PROPERTY = "user.name";
    public static final String ROLE_ID_PROPERTY = "role.id";
    public static final String ROLE_NAME_PROPERTY = "role.name";

}
