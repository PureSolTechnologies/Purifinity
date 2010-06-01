package com.puresol.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class FileUtilities {

	private static final Logger logger = Logger.getLogger(FileUtilities.class);

	public static File classToRelativePackagePath(Class<?> clazz) {
		return new File(clazz.getName().replaceAll("\\.", "/") + ".java");
	}

	public static File addPaths(File path1, File path2) {
		return new File(path1, path2.toString());
	}

	public static boolean writeFile(File directory, File fileName, String text) {
		RandomAccessFile ra = null;
		try {
			File destination = FileUtilities.addPaths(directory, fileName);
			File parent = destination.getParentFile();
			if (!parent.exists()) {
				if (!parent.mkdirs()) {
					return false;
				}
			}
			ra = new RandomAccessFile(destination, "rw");
			ra.setLength(0);
			ra.writeBytes(text);
			ra.close();
			return true;
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		if (ra != null) {
			try {
				ra.close();
			} catch (IOException e) {
			}
		}
		return false;
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
		if ((from.getPath().startsWith("/")) && (to.getPath().startsWith("/"))) {
			return getRelativePathForAbsolutes(from, to);
		} else if ((!from.getPath().startsWith("/"))
				&& (!to.getPath().startsWith("/"))) {
			return getRelativePathForRelatives(from, to);
		}
		return null;
	}

	private static File getRelativePathForAbsolutes(File from, File to) {
		String[] toSplit = to.getPath().split("/");
		String[] fromSplit = from.getPath().split("/");
		int matching = 0;
		while (toSplit[matching].equals(fromSplit[matching])) {
			matching++;
			if ((matching >= toSplit.length) || (matching >= fromSplit.length)) {
				break;
			}
		}
		String result = "";
		for (int i = matching; i < fromSplit.length - 1; i++) {
			result += "../";
		}
		for (int i = matching; i < toSplit.length; i++) {
			result += toSplit[i];
			if (i < toSplit.length - 1) {
				result += "/";
			}
		}
		return new File(result);
	}

	private static File getRelativePathForRelatives(File from, File to) {
		String parent = from.getParent();
		if (parent == null) {
			return to;
		}
		parent = parent.replaceAll("[^/]+", "..");
		return FileUtilities.addPaths(new File(parent), to);
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
		while (normalizedFile.contains("//")) {
			normalizedFile = normalizedFile.replaceAll("//", "/");
		}
		// remove all redundant '..'
		Pattern pattern = Pattern.compile("([^\\./]+/\\.\\.)");
		Matcher matcher = pattern.matcher(normalizedFile);
		while (matcher.find()) {
			normalizedFile = normalizedFile.replace(matcher.group(1), "")
					.replaceAll("//", "/");
			if ((!isAbsolute) && (normalizedFile.startsWith("/"))) {
				normalizedFile = normalizedFile.replaceFirst("/", "");
			} else if ((isAbsolute) && (!normalizedFile.startsWith("/"))) {
				normalizedFile = "/" + normalizedFile;
			}
			matcher = pattern.matcher(normalizedFile);
		}
		return new File(normalizedFile);
	}

	public static String readFileToString(File directory, File fileName) {
		RandomAccessFile ra = null;
		try {
			StringBuffer text = new StringBuffer();
			File source = FileUtilities.addPaths(directory, fileName);
			ra = new RandomAccessFile(source, "r");
			String line;
			while ((line = ra.readLine()) != null) {
				text.append(line);
			}
			ra.close();
			return text.toString();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		if (ra != null) {
			try {
				ra.close();
			} catch (IOException e) {
			}
		}
		return "";
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
}
