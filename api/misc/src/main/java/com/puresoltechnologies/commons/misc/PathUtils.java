package com.puresoltechnologies.commons.misc;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains several static methods for easier access to standard
 * functionality.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PathUtils {

	/**
	 * This method converts a Class into a File which contains the package path.
	 * This is used to access Jar contents or the content of source directories
	 * to find source or class files.
	 * 
	 * @param clazz
	 * @return
	 */
	public static File classToRelativePackagePath(Class<?> clazz) {
		if (File.separator.equals("/")) {
			return new File(clazz.getName().replaceAll("\\.", File.separator)
					+ ".java");
		} else {
			return new File(clazz.getName().replaceAll("\\.",
					File.separator + File.separator)
					+ ".java");
		}
	}

	/**
	 * (Found at:
	 * http://stackoverflow.com/questions/204784/how-to-construct-a-relative
	 * -path-in-java-from-two-absolute-paths-or-urls)
	 * 
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
		int commonLength = common.length();
		if (commonLength < toPath.length()) {
			relative.append(toPath.substring(commonLength));
		}
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
		// remove all '//'...
		String quotedSeparator = Pattern.quote(File.separator);
		String replacementSeparator = File.separator.equals("/") ? File.separator
				: File.separator + File.separator;
		boolean isAbsolute = file.isAbsolute()
				|| normalizedFile.startsWith(File.separator);
		while (normalizedFile.contains(replacementSeparator
				+ replacementSeparator)) {
			normalizedFile = normalizedFile.replaceAll(quotedSeparator
					+ quotedSeparator, replacementSeparator);
		}
		// remove all redundant '..'
		Pattern pattern = Pattern.compile("([^\\." + replacementSeparator
				+ "]+" + quotedSeparator + "\\.\\.)");
		Matcher matcher = pattern.matcher(normalizedFile);
		while (matcher.find()) {
			normalizedFile = normalizedFile.replace(matcher.group(1), "")
					.replaceAll(quotedSeparator + quotedSeparator,
							replacementSeparator);
			if ((!isAbsolute) && (normalizedFile.startsWith(File.separator))) {
				normalizedFile = normalizedFile.replaceFirst(quotedSeparator,
						"");
			} else if ((isAbsolute)
					&& (!normalizedFile.startsWith(File.separator))) {
				normalizedFile = File.separator + normalizedFile;
			}
			matcher = pattern.matcher(normalizedFile);
		}
		/* remove all '/./' and /. at the end */
		pattern = Pattern.compile(quotedSeparator + "\\." + quotedSeparator);
		matcher = pattern.matcher(normalizedFile);
		while (matcher.find()) {
			normalizedFile = normalizedFile.replace(matcher.group(0),
					replacementSeparator);
			matcher = pattern.matcher(normalizedFile);
		}
		if (normalizedFile.endsWith(File.separator + ".")) {
			normalizedFile = normalizedFile.substring(0,
					normalizedFile.length() - 2);
		}
		return new File(normalizedFile);
	}

}
