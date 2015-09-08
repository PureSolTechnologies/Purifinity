package com.puresoltechnologies.purifinity.server.database.hadoop.bloob;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.fs.permission.FsPermission;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.server.database.hadoop.connector.api.BloobServiceRemote;

@Stateless
public class BloobService implements BloobServiceRemote {

    private static final String DATA_DIRECTORY = "/data/Purifinity/bloob";

    private final Path dataPath = new Path(DATA_DIRECTORY);

    @Inject
    private FileSystem fileSystem;

    @PostConstruct
    public void initialize() {
	try {
	    if (!fileSystem.exists(dataPath)) {
		if (!fileSystem.mkdirs(dataPath, new FsPermission("755"))) {
		    throw new RuntimeException("Could not create Bloob data directory in HDFS.");
		}
	    }
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
	Path path = new Path(dataPath, child.toString());
	return path;
    }

    @Override
    public boolean isAvailable(HashId hashId) throws IOException {
	return fileSystem.exists(createPath(hashId));
    }

    @Override
    public long getFileSize(HashId hashId) throws IOException {
	RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(createPath(hashId), false);
	if (!files.hasNext()) {
	    throw new FileNotFoundException("Could not find file with hash id '" + hashId + "'.");
	}
	return files.next().getLen();
    }

    @Override
    public byte[] readRawFile(HashId hashId) throws IOException {
	if (!isAvailable(hashId)) {
	    throw new FileNotFoundException("Could not find file with hash id '" + hashId + "'.");
	}
	try (FSDataInputStream inputStream = fileSystem.open(createPath(hashId));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
	    IOUtils.copy(inputStream, outputStream);
	    return outputStream.toByteArray();
	}
    }

    @Override
    public void storeRawFile(HashId hashId, byte[] bytes) throws IOException {
	Path path = createPath(hashId);
	try (FSDataOutputStream outputStream = fileSystem.create(path, false);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);) {
	    IOUtils.copy(inputStream, outputStream);
	}
    }

    @Override
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
