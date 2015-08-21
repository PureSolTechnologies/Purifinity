package com.puresoltechnologies.purifinity.server.database.hadoop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.fs.permission.FsPermission;
import org.junit.Test;

public class HadoopConnectionIT {

    @Test
    public void test() throws IOException {
	Configuration configuration = HadoopClientHelper.createConfiguration();

	FileSystem fileSystem = FileSystem.newInstance(configuration);
	assertNotNull(fileSystem);
	if (fileSystem.exists(new Path("/test"))) {
	    assertTrue(fileSystem.delete(new Path("/test"), true));
	}
	if (fileSystem.exists(new Path("/tmp"))) {
	    assertTrue(fileSystem.delete(new Path("/tmp"), true));
	}
	RemoteIterator<LocatedFileStatus> files = fileSystem.listLocatedStatus(new Path("/"));
	assertFalse(files.hasNext());

	assertTrue(fileSystem.mkdirs(new Path("/test"), new FsPermission("700")));

	files = fileSystem.listLocatedStatus(new Path("/"));
	assertTrue(files.hasNext());
	while (files.hasNext()) {
	    LocatedFileStatus file = files.next();
	    System.out.println(file.getPath().getName());
	}
    }

    @Test
    public void test2() {
	String hash = "12345678901234567890";
	String child = hash.substring(0, 2) + "/" + hash.substring(2, 4) + "/" + hash.substring(4, 6) + "/"
		+ hash.substring(6, 8) + "/" + hash.substring(8);
	System.out.println(child);
    }
}
