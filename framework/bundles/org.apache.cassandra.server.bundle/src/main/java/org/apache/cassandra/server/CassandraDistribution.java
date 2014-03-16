package org.apache.cassandra.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CassandraDistribution {

	private static final Logger logger = LoggerFactory
			.getLogger(CassandraDistribution.class);

	static final String CASSANDRA_VERSION = "2.0.6";
	static final String DIRECTORY_REPLACEMENT = "apache-cassandra-"
			+ CASSANDRA_VERSION + "/";
	static final String DISTRIBUTION_RESOURCE = "/cassandra-distribution-"
			+ CASSANDRA_VERSION + "-binary.tar.gz";

	public static void extract(File targetDirectory) throws IOException {
		logger.info("Cassandra distribution will be unpacked.");

		File completedFile = new File(targetDirectory, ".completed");
		if (completedFile.exists()) {
			logger.info("Cassandra was extracted already.");
			return;
		}

		URL resource = getDistributionResource();
		checkAndCreateTargetDirectory(targetDirectory);
		extractDistribution(resource, targetDirectory);

		if (!completedFile.createNewFile()) {
			throw new IOException("Could not created .completed file.");
		}
		logger.info("Cassandra distribution was unpacked.");
	}

	private static URL getDistributionResource() throws IOException {
		URL resource = CassandraServer.class.getResource(DISTRIBUTION_RESOURCE);
		if (resource == null) {
			throw new IOException(
					"Cassandra distribution resource not present.");
		}
		return resource;
	}

	private static void checkAndCreateTargetDirectory(File targetDirectory)
			throws IOException {
		if (!targetDirectory.exists()) {
			if (!targetDirectory.mkdirs()) {
				throw new IOException("Could not create target directory '"
						+ targetDirectory + "'.");
			}
		} else {
			if (!targetDirectory.isDirectory()) {
				throw new IOException("Specified target directory '"
						+ targetDirectory + "' is not a directory.");
			}
		}
	}

	private static void extractDistribution(URL resource, File targetDirectory)
			throws IOException, FileNotFoundException {
		try (InputStream distributionResource = resource.openStream();
				GZIPInputStream gzipInputStream = new GZIPInputStream(
						distributionResource);
				TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(
						gzipInputStream)) {
			TarArchiveEntry archiveEntry;
			while ((archiveEntry = tarArchiveInputStream.getNextTarEntry()) != null) {
				extractTarEntry(tarArchiveInputStream, archiveEntry,
						targetDirectory);
			}
		}
	}

	private static void extractTarEntry(
			TarArchiveInputStream tarArchiveInputStream,
			TarArchiveEntry archiveEntry, File targetDirectory)
			throws IOException, FileNotFoundException {
		String file = archiveEntry.getName();
		file = file.replaceAll(DIRECTORY_REPLACEMENT, "");
		File targetFile = new File(targetDirectory, file);
		if (archiveEntry.isDirectory()) {
			extractDirectory(targetFile);
		} else if (archiveEntry.isFile()) {
			extractFile(tarArchiveInputStream, targetFile);
		} else {
			throw new IOException("Unsupported content type for '" + file
					+ "'.");
		}
	}

	private static void extractDirectory(File targetFile) throws IOException {
		logger.info("Extract directory '" + targetFile + "'.");
		if (!targetFile.exists()) {
			if (!targetFile.mkdirs()) {
				throw new IOException("Could not create directory '"
						+ targetFile + "'.");
			}
		}
	}

	private static void extractFile(
			TarArchiveInputStream tarArchiveInputStream, File targetFile)
			throws IOException, FileNotFoundException {
		logger.info("Extract file '" + targetFile + "'.");
		if (!targetFile.exists()) {
			if (!targetFile.createNewFile()) {
				throw new IOException("Could not create file '" + targetFile
						+ "'.");
			}
			try (FileOutputStream fileOutputStream = new FileOutputStream(
					targetFile)) {
				IOUtils.copy(tarArchiveInputStream, fileOutputStream);
			}
		} else {
			logger.warn("File '" + targetFile + "' already exists.");
		}
	}

}
