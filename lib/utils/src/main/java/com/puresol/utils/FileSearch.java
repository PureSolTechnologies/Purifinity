/****************************************************************************
 *
 *   FileSearch.java
 *   -------------------
 *   copyright            : (c) 2009-2011 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ****************************************************************************/

/****************************************************************************
 *
 * Copyright 2009-2011 PureSol-Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ****************************************************************************/

package com.puresol.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.puresol.trees.FileTree;

/**
 * This class was implemented for recursive file search.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileSearch {

    public static String wildcardsToRegExp(String pattern) {
	pattern = pattern.replaceAll("\\.", "\\\\.");
	if (File.separator.equals("\\")) {
	    pattern = pattern.replaceAll("\\*", "[^\\\\\\\\]*");
	    pattern = pattern.replaceAll("\\?", "[^\\\\\\\\]");
	} else {
	    pattern = pattern.replaceAll("\\*", "[^" + File.separator + "]*");
	    pattern = pattern.replaceAll("\\?", "[^" + File.separator + "]");
	}
	return pattern;
    }

    /**
     * This method searches a directory recursively. A pattern specifies which
     * files are to be put into the output list.
     * 
     * @param directory
     *            is the directory where the recursive search is to be started.
     * @param pattern
     *            is a Apache like pattern to specify the files which are to be
     *            put into the output list.
     * @return
     */
    public static List<File> find(File directory, String pattern) {
	pattern = wildcardsToRegExp(pattern);
	List<File> files = findFilesInDirectory(directory,
		Pattern.compile(pattern), true);
	List<File> result = new ArrayList<File>();
	for (File file : files) {
	    String fileString = file.getPath().substring(
		    directory.getPath().length());
	    result.add(new File(fileString));
	}
	return result;
    }

    /**
     * This class is the recursive part of the file search.
     * 
     * @param directory
     * @param pattern
     * @param scanRecursive
     * @return
     */
    private static List<File> findFilesInDirectory(File directory,
	    Pattern pattern, boolean scanRecursive) {
	List<File> files = new ArrayList<File>();
	String[] filesInDirectory = directory.list();
	if (filesInDirectory == null) {
	    return files;
	}
	for (String fileToCheck : filesInDirectory) {
	    File file = new File(directory, fileToCheck);
	    if (file.isFile()) {
		if (pattern.matcher(fileToCheck).matches()) {
		    files.add(file);
		}
	    } else if (file.isDirectory()) {
		if (scanRecursive) {
		    files.addAll(findFilesInDirectory(file, pattern,
			    scanRecursive));
		}
	    }
	}
	return files;
    }

    public static FileTree getFileTree(File directory,
	    FileSearchConfiguration configuration) {

	List<Pattern> dirIncludePatterns = convertStringListToPatternList(configuration
		.getDirectoryIncludes());
	List<Pattern> dirExcludePatterns = convertStringListToPatternList(configuration
		.getDirectoryExcludes());
	List<Pattern> fileIncludePatterns = convertStringListToPatternList(configuration
		.getFileIncludes());
	List<Pattern> fileExcludePatterns = convertStringListToPatternList(configuration
		.getFileExcludes());

	FileTree fileTree = new FileTree(null, directory.getPath());
	fileTree = getFileTree(directory, fileTree, dirIncludePatterns,
		dirExcludePatterns, fileIncludePatterns, fileExcludePatterns,
		configuration.isIgnoreHidden());
	return fileTree;
    }

    private static List<Pattern> convertStringListToPatternList(
	    List<String> strings) {
	List<Pattern> patterns = new ArrayList<Pattern>();
	for (String string : strings) {
	    patterns.add(Pattern.compile(FileSearch.wildcardsToRegExp(string)));
	}
	return patterns;
    }

    private static FileTree getFileTree(File directory, FileTree fileTree,
	    List<Pattern> dirIncludes, List<Pattern> dirExcludes,
	    List<Pattern> fileIncludes, List<Pattern> fileExcludes,
	    boolean ignoreHidden) {
	String[] fileNames = directory.list();
	for (String fileName : fileNames) {
	    File file = new File(directory, fileName);
	    if (file.isDirectory()
		    && use(fileName, file.isHidden(), dirIncludes, dirExcludes,
			    ignoreHidden)) {
		FileTree fileSubTree = new FileTree(fileTree, fileName);
		getFileTree(file, fileSubTree, dirIncludes, dirExcludes,
			fileIncludes, fileExcludes, ignoreHidden);
	    } else if (file.isFile()
		    && use(fileName, file.isHidden(), fileIncludes,
			    fileExcludes, ignoreHidden)) {
		new FileTree(fileTree, fileName);
	    }
	}
	return fileTree;
    }

    /**
     * Checks whether a file or directory name is to be used.
     * 
     * @param fileName
     * @param hidden
     * @param includes
     * @param excludes
     * @param ignoreHidden
     * @return
     */
    private static boolean use(String fileName, boolean hidden,
	    List<Pattern> includes, List<Pattern> excludes, boolean ignoreHidden) {
	for (Pattern includePattern : includes) {
	    if (includePattern.matcher(fileName).matches()) {
		return true;
	    }
	}
	for (Pattern excludePattern : excludes) {
	    if (excludePattern.matcher(fileName).matches()) {
		return false;
	    }
	}
	if (ignoreHidden) {
	    return !hidden;
	}
	return true;
    }
}
