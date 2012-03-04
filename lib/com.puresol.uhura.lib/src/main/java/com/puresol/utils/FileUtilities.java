package com.puresol.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.trees.FileTree;

/**
 * This class contains several static methods for easier access to standard
 * functionality.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileUtilities {

    private static final Logger logger = LoggerFactory
	    .getLogger(FileUtilities.class);

    /**
     * This method converts a Class into a File which contains the package path.
     * This is used to access Jar contents or the content of source directories
     * to find source or class files.
     * 
     * @param clazz
     * @return
     */
    public static File classToRelativePackagePath(Class<?> clazz) {
	return new File(clazz.getName().replaceAll("\\.", File.separator)
		+ ".java");
    }

    /**
     * This method checks for the requirement for an update.
     * 
     * If a the target file exists and the modification time is greater than the
     * modification time of the source file, we do not need to analyze
     * something.
     * 
     * @param sourceFile
     * @param targetFile
     */
    public static boolean isUpdateRequired(File sourceFile, File targetFile) {
	if (targetFile.exists()) {
	    if (targetFile.lastModified() > sourceFile.lastModified()) {
		return false;
	    }
	}
	return true;
    }

    /**
     * This method writes the content of a String into a file specified by a
     * directory and its fileName.
     * 
     * @param directory
     * @param fileName
     * @param text
     * @return
     */
    public static boolean writeFile(File directory, File fileName, String text) {
	try {
	    File destination = new File(directory, fileName.getPath());
	    File parent = destination.getParentFile();
	    if (!parent.exists()) {
		if (!parent.mkdirs()) {
		    return false;
		}
	    }
	    RandomAccessFile ra = new RandomAccessFile(destination, "rw");
	    try {
		ra.setLength(0);
		ra.writeBytes(text);
	    } finally {
		ra.close();
	    }
	    return true;
	} catch (FileNotFoundException e) {
	    logger.error(e.getMessage(), e);
	    return false;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    return false;
	}
    }

    /**
     * Get the relative path from one file to another, specifying the directory
     * separator. If one of the provided resources does not exist, it is assumed
     * to be a file unless it ends with '/' or '\'.
     * 
     * @param target
     *            targetPath is calculated to this file
     * @param base
     *            basePath is calculated from this file
     * @param separator
     *            directory separator. The platform default is not assumed so
     *            that we can test Unix behaviour when running on Windows (for
     *            example)
     * @return
     * @throws PathResolutionException
     */
    public static String getRelativePath(String fromPath, String toPath,
	    String pathSeparator) throws PathResolutionException {
	if (fromPath.equals(toPath)) {
	    return "";
	}
	// We need the -1 argument to split to make sure we get a trailing
	// "" token if the base ends in the path separator and is therefore
	// a directory. We require directory paths to end in the path
	// separator -- otherwise they are indistinguishable from files.
	String[] from = fromPath.split(Pattern.quote(pathSeparator), -1);
	String[] to = toPath.split(Pattern.quote(pathSeparator), 0);

	// First get all the common elements. Store them as a string,
	// and also count how many of them there are.
	StringBuilder common = new StringBuilder();

	int commonIndex = 0;
	while ((commonIndex < to.length) && (commonIndex < from.length)
		&& (to[commonIndex].equals(from[commonIndex]))) {
	    common.append(to[commonIndex] + pathSeparator);
	    commonIndex++;
	}

	if (commonIndex == 0) {
	    // No single common path element. This most
	    // likely indicates differing drive letters, like C: and D:.
	    // These paths cannot be relativized.
	    throw new PathResolutionException(
		    "No common path element found for '" + toPath + "' and '"
			    + fromPath + "'");
	}

	StringBuffer relative = new StringBuffer();

	if (from.length != commonIndex) {
	    int numDirsUp = from.length - commonIndex - 1;
	    for (int i = 0; i < numDirsUp; i++) {
		relative.append(".." + pathSeparator);
	    }
	}
	relative.append(toPath.substring(common.length()));
	return relative.toString();
    }

    /**
     * This method normalized paths by removing '//' and redundant '..'.
     * 
     * @param file
     * @return
     */
    public static File normalizePath(File file) {
	if (file == null) {
	    return new File("");
	}
	String normalizedFile = file.getPath();
	boolean isAbsolute = file.isAbsolute();
	// remove all '//'...
	while (normalizedFile.contains(File.separator + File.separator)) {
	    normalizedFile = normalizedFile.replaceAll(File.separator
		    + File.separator, File.separator);
	}
	// remove all redundant '..'
	Pattern pattern = Pattern.compile("([^\\." + File.separator + "]+"
		+ File.separator + "\\.\\.)");
	Matcher matcher = pattern.matcher(normalizedFile);
	while (matcher.find()) {
	    normalizedFile = normalizedFile
		    .replace(matcher.group(1), "")
		    .replaceAll(File.separator + File.separator, File.separator);
	    if ((!isAbsolute) && (normalizedFile.startsWith(File.separator))) {
		normalizedFile = normalizedFile
			.replaceFirst(File.separator, "");
	    } else if ((isAbsolute)
		    && (!normalizedFile.startsWith(File.separator))) {
		normalizedFile = File.separator + normalizedFile;
	    }
	    matcher = pattern.matcher(normalizedFile);
	}
	/* remove all '/./' and /. at the end */
	pattern = Pattern.compile(File.separator + "\\." + File.separator);
	matcher = pattern.matcher(normalizedFile);
	while (matcher.find()) {
	    normalizedFile = normalizedFile.replace(matcher.group(0), "/");
	    matcher = pattern.matcher(normalizedFile);
	}
	if (normalizedFile.endsWith(File.separator + ".")) {
	    normalizedFile = normalizedFile.substring(0,
		    normalizedFile.length() - 2);
	}
	return new File(normalizedFile);
    }

    public static String readFileToString(File directory, String fileName) {
	return readFileToString(new File(directory, fileName));
    }

    public static String readFileToString(File file) {
	try {
	    StringBuffer text = new StringBuffer();
	    RandomAccessFile ra = new RandomAccessFile(file, "r");
	    try {
		String line;
		while ((line = ra.readLine()) != null) {
		    text.append(line);
		    text.append("\n");
		}
	    } finally {
		ra.close();
	    }
	    return text.toString();
	} catch (FileNotFoundException e) {
	    logger.error(e.getMessage(), e);
	    return "";
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    return "";
	}
    }

    /**
     * Utility method used to delete the profile directory when run as a
     * stand-alone application.
     * 
     * @param file
     *            The file to recursively delete.
     **/
    public static void deleteFileOrDir(File file) {
	if (file.isDirectory()) {
	    for (File child : file.listFiles()) {
		deleteFileOrDir(child);
	    }
	}
	file.delete();
    }

    public static FileTree convertFileListToTree(String rootName,
	    List<File> files) {
	FileTree top = new FileTree(null, rootName);
	for (File file : files) {
	    String[] pathComponents = file.getPath().split(File.separator);
	    FileTree current = top;
	    for (String component : pathComponents) {
		if (component.isEmpty()) {
		    continue;
		}
		FileTree child = current.getChild(component);
		if (child == null) {
		    child = new FileTree(current, component);
		}
		current = child;
	    }
	}
	return top;
    }
}
