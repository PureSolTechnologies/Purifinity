package com.puresol.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.puresol.trees.FileTree;

/**
 * This class contains several static methods for easier access to standard
 * functionality.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileUtilities {

	private static final Logger logger = Logger.getLogger(FileUtilities.class);

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
	 * This method creates a relative path from a file to another. Both file
	 * paths have to either start with a slash or not. A mixture is not allowed.
	 * The situation in the case of two absolute paths are obvious, but for
	 * relative paths it is assumed, that both start at the same location.
	 * 
	 * @param from
	 *            the file where the starting directory is.
	 * @param to
	 *            is the file where to point to from the from directory.
	 * @return A relative path is returned.
	 */
	public static File getRelativePath(File from, File to) {
		from = normalizePath(from);
		to = normalizePath(to);
		if ((from.getPath().startsWith(File.separator))
				&& (to.getPath().startsWith(File.separator))) {
			return getRelativePathForAbsolutes(from, to);
		} else if ((!from.getPath().startsWith(File.separator))
				&& (!to.getPath().startsWith(File.separator))) {
			return getRelativePathForRelatives(from, to);
		}
		return null;
	}

	private static File getRelativePathForAbsolutes(File from, File to) {
		String[] toSplit = to.getPath().split(File.separator);
		String[] fromSplit = from.getPath().split(File.separator);
		int matching = 0;
		while (toSplit[matching].equals(fromSplit[matching])) {
			matching++;
			if ((matching >= toSplit.length) || (matching >= fromSplit.length)) {
				break;
			}
		}
		StringBuffer result = new StringBuffer();
		for (int i = matching; i < fromSplit.length - 1; i++) {
			result.append(".." + File.separator);
		}
		for (int i = matching; i < toSplit.length; i++) {
			result.append(toSplit[i]);
			if (i < toSplit.length - 1) {
				result.append(File.separator);
			}
		}
		return FileUtilities.normalizePath(new File(result.toString()));
	}

	private static File getRelativePathForRelatives(File from, File to) {
		String parent = from.getParent();
		if ((parent == null) || (parent.isEmpty())) {
			return to;
		}
		parent = parent.replaceAll("[^" + File.separator + "]+", "..");
		return FileUtilities.normalizePath(new File(new File(parent), to
				.getPath()));
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
