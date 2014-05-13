package com.puresoltechnologies.commons.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 * This method performs a simple copy of sourceFile to targetFile.
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
	public static void copy(File sourceFile, File targetFile)
			throws IOException {
		FileInputStream in = new FileInputStream(sourceFile);
		try {
			FileOutputStream out = new FileOutputStream(targetFile);
			try {
				copy(in, out);
			} finally {
				out.close();
			}
		} finally {
			in.close();
		}
	}

	public static void copy(InputStream in, OutputStream out)
			throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		do {
			len = in.read(buffer);
			if (len > 0) {
				out.write(buffer, 0, len);
			}
		} while (len > 0);
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

	public static String readFileToString(File directory, String fileName)
			throws IOException {
		return readFileToString(new File(directory, fileName));
	}

	public static String readFileToString(File file) throws IOException {
		byte[] buffer = new byte[1024];
		StringBuilder text = new StringBuilder();
		FileInputStream inStream = new FileInputStream(file);
		try {
			int len;
			do {
				len = inStream.read(buffer);
				if (len > 0) {
					text.append(new String(buffer, 0, len));
				}
			} while (len == buffer.length);
			return text.toString();
		} finally {
			inStream.close();
		}
	}

	/**
	 * Utility method used to delete the profile directory when run as a
	 * stand-alone application.
	 * 
	 * @param file
	 *            The file to recursively delete.
	 * @throws IOException
	 **/
	public static void deleteFileOrDir(File file) throws IOException {
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				deleteFileOrDir(child);
			}
		}
		if (!file.delete()) {
			throw new IOException("Could not remove '" + file + "'!");
		}
	}

	public static HashId createHashId(File file, HashAlgorithm algorithm)
			throws IOException {
		if (!file.isFile()) {
			throw new IOException("'" + file + "' is not a file!");
		}
		String content = readFileToString(file);
		return new HashId(algorithm, HashCodeGenerator.get(algorithm, content));
	}
}
