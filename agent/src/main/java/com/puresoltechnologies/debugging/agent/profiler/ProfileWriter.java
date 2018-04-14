package com.puresoltechnologies.debugging.agent.profiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

import com.puresoltechnologies.debugging.agent.Configuration;
import com.puresoltechnologies.streaming.binary.BinaryOutputStream;
import com.puresoltechnologies.streaming.streams.OptimizedFileOutputStream;

public class ProfileWriter {

    private static BinaryOutputStream binaryOutputStream = null;
    private static Object lock = new Object();

    public static void initialize() throws FileNotFoundException {
	File outputDirectory = Configuration.getOutputDirectory();
	File profileFile = new File(outputDirectory, "profile.raw");
	binaryOutputStream = new BinaryOutputStream(new OptimizedFileOutputStream(profileFile),
		ByteOrder.LITTLE_ENDIAN);
    }

    public static void shutdown() throws IOException {
	binaryOutputStream.close();
    }

    public static void printStartTime(Thread thread, int classId, short methodId, long start) {
	try {
	    synchronized (lock) {
		binaryOutputStream.write(0);
		binaryOutputStream.writeNulTerminatedString(thread.getName(), Charset.defaultCharset());
		binaryOutputStream.writeSignedInt(classId);
		binaryOutputStream.writeSignedShort(methodId);
		binaryOutputStream.writeSignedLong(start);
	    }
	} catch (IOException e) {
	    // intentionaly left empty
	}
    }

    public static void printEndTime(Thread thread, int classId, short methodId, long end) {
	try {
	    synchronized (lock) {
		binaryOutputStream.write(1);
		binaryOutputStream.writeNulTerminatedString(thread.getName(), Charset.defaultCharset());
		binaryOutputStream.writeSignedInt(classId);
		binaryOutputStream.writeSignedShort(methodId);
		binaryOutputStream.writeSignedLong(end);
	    }
	} catch (IOException e) {
	    // intentionaly left empty
	}
    }

}
