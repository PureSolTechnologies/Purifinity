package com.puresol.source;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.i18n4java.utils.FileSearch;

import org.apache.log4j.Logger;

import com.puresol.config.ConfigFile;

public class SourceHeader {

	private static final Logger logger = Logger.getLogger(SourceHeader.class);

	public static void addHeaderToFiles(File template, File about,
			File directory) {
		addHeaderToFiles(template, about, directory, "*.java");
	}

	public static void addHeaderToFiles(File template, File about,
			File directory, String pattern) {
		for (File file : FileSearch.find(directory, pattern)) {
			logger.info("Processing file '" + file.getPath() + "'...");
			addHeaderToFile(template, about, file);
			logger.info("done.");
		}
	}

	public static void addHeaderToFile(File template, File about, File file) {
		if (!template.exists()) {
			logger.error("template '" + template.getPath()
					+ "' is not existing!");
		}
		try {
			String bakFile = file.getPath() + "~";
			file.renameTo(new File(bakFile));
			RandomAccessFile in;
			in = new RandomAccessFile(bakFile, "r");
			RandomAccessFile out = new RandomAccessFile(file, "rw");

			String line;
			line = in.readLine();
			while ((!line.contains("package ")) && (line != null)) {
				line = in.readLine();
			}

			ConfigFile aboutFile = new ConfigFile(about);
			writeNewHeader(out, file, template, aboutFile);
			aboutFile.close();

			while (line != null) {
				out.writeBytes(line + "\n");
				line = in.readLine();
			}
			out.close();
			in.close();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private static void writeNewHeader(RandomAccessFile out, File outFile,
			File template, ConfigFile about) {
		try {
			RandomAccessFile in = new RandomAccessFile(template, "r");
			String line = in.readLine();
			while (line != null) {
				line = line.replaceAll("%SOURCEFILE%", outFile.getName());
				line = line.replaceAll("%OWNER%",
						about.read("GENERAL", "owner"));
				line = line.replaceAll("%YEARS%",
						about.read("GENERAL", "years"));
				line = line.replaceAll("%COPYRIGHT%",
						about.read("GENERAL", "copyright"));
				line = line.replaceAll("%AUTHOR%",
						about.read("GENERAL", "author"));
				line = line.replaceAll("%BUGREPORT%",
						about.read("GENERAL", "bugreport"));
				line = line.replaceAll("%VERSION%",
						about.read("GENERAL", "version"));
				out.writeBytes(line + "\n");
				line = in.readLine();
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
