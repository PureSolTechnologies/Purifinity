package com.puresoltechnologies.purifinity.server.database.hadoop.utils.bloob;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.slf4j.Logger;

import com.puresoltechnologies.commons.misc.hash.HashId;

@Stateless
public class BloobService {

    private static final String FILE_DIRECTORY = "/apps/Purifinity/files";

    private final Path filePath = new Path(FILE_DIRECTORY);

    @Inject
    private Logger logger;

    @Inject
    private FileSystem fileSystem;

    @PostConstruct
    public void initialize() {
	try {
	    logger.info("Initialize Bloob service...");
	    if (!fileSystem.exists(filePath)) {
		throw new RuntimeException("Could not initialize Bloob Service due to missing file directory in HDFS.");
	    }
	    FileStatus fileStatus = fileSystem.getFileStatus(filePath);
	    if (!fileStatus.isDirectory()) {
		throw new RuntimeException(
			"Could not initialize Bloob Service due to missing file directory in HDFS. Path exists, but it is not a directory.");
	    }
	    logger.info("Bloob service initialized.");
	} catch (IOException e) {
	    throw new RuntimeException("Could not initialize Bloob service.", e);
	}
    }

    private Path createPath(HashId hashId) {
	String hash = hashId.getHash();
	StringBuffer child = new StringBuffer();
	child.append(hash.substring(0, 2));
	child.append("/");
	child.append(hash.substring(2, 4));
	child.append("/");
	child.append(hash.substring(4, 6));
	child.append("/");
	child.append(hash.substring(6, 8));
	child.append("/");
	child.append(hash.substring(8));
	return new Path(filePath, child.toString());
    }

    public boolean isAvailable(HashId hashId) throws IOException {
	return fileSystem.exists(createPath(hashId));
    }

    public long getFileSize(HashId hashId) throws IOException {
	RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(createPath(hashId), false);
	if (!files.hasNext()) {
	    throw new FileNotFoundException("Could not find file with hash id '" + hashId + "'.");
	}
	return files.next().getLen();
    }

    public InputStream readRawFile(HashId hashId) throws IOException {
	if (!isAvailable(hashId)) {
	    throw new FileNotFoundException("Could not find file with hash id '" + hashId + "'.");
	}
	return fileSystem.open(createPath(hashId));
    }

    public void storeRawFile(HashId hashId, InputStream buffer) throws IOException {
	Path path = createPath(hashId);
	try (FSDataOutputStream outputStream = fileSystem.create(path, false);) {
	    IOUtils.copy(buffer, outputStream);
	}
    }

    public boolean removeRawFile(HashId hashId) throws IOException {
	if (!isAvailable(hashId)) {
	    return false;
	}
	if (!fileSystem.delete(createPath(hashId), false)) {
	    throw new IOException("Could not delete file for hash id '" + hashId + "'.");
	}
	return true;
    }
}
