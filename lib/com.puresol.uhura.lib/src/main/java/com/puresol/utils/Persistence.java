package com.puresol.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class is for simple object persistence. It stores and loads objects to
 * and from files by Java's internal serialization mechanism. This class is just
 * for simple usage.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Persistence {

	/**
	 * This method persists an object into a specified file.
	 * 
	 * @param o
	 * @param file
	 * @throws PersistenceException
	 */
	public static void persist(Object o, File file) throws IOException {
		File directory = file.getParentFile();
		if ((directory != null) && (!directory.exists())) {
			if (!directory.mkdirs()) {
				throw new IOException("Could not create necessary directory '"
						+ directory + "'!");
			}
		}
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				file));
		try {
			oos.writeObject(o);
		} finally {
			oos.close();
		}
	}

	public static Object restore(File file) throws IOException,
			PersistenceException {
		if (!file.exists()) {
			throw new FileNotFoundException("File '" + file
					+ "' was not found!");
		}
		return restore(new FileInputStream(file));
	}

	public static Object restore(InputStream inputStream) throws IOException,
			PersistenceException {
		try {
			if (inputStream == null) {
				throw new IOException("Input stream is null!");
			}
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			try {
				return ois.readObject();
			} finally {
				ois.close();
			}
		} catch (ClassNotFoundException e) {
			throw new PersistenceException(e);
		}
	}

}