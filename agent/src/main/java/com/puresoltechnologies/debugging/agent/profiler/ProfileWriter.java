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
	binaryOutputStream.flush();
	binaryOutputStream.close();
    }

    public static void printTime(Class<?> clazz, String method, long time, long invocations) {
	try {
	    synchronized (lock) {
		binaryOutputStream.write((byte) 1);
		binaryOutputStream.writeNulTerminatedString(clazz.getName(), Charset.defaultCharset());
		binaryOutputStream.writeNulTerminatedString(method, Charset.defaultCharset());
		binaryOutputStream.writeSignedLong(time);
		binaryOutputStream.writeSignedLong(invocations);
	    }
	} catch (IOException e) {
	    // intentionaly left empty
	}
    }

}
