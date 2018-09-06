package com.puresoltechnologies.toolshed.agent.profiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

import com.puresoltechnologies.streaming.binary.BinaryOutputStream;
import com.puresoltechnologies.streaming.streams.OptimizedFileOutputStream;
import com.puresoltechnologies.toolshed.agent.Configuration;
import com.puresoltechnologies.toolshed.agent.MethodDefinition;

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

    public static void printTime(MethodDefinition methodDefinition, long time, long invocations) {
	try {
	    synchronized (lock) {
		System.out.println(
			"write profile entry:" + methodDefinition.toString() + ": " + invocations + "x " + time + "ms");
		binaryOutputStream.write((byte) 1);
		Charset defaultCharset = Charset.defaultCharset();
		binaryOutputStream.writeNulTerminatedString(methodDefinition.getClassName(), defaultCharset);
		binaryOutputStream.writeNulTerminatedString(methodDefinition.getMethodName(), defaultCharset);
		binaryOutputStream.writeNulTerminatedString(methodDefinition.getDescriptor(), defaultCharset);
		binaryOutputStream.writeSignedLong(time);
		binaryOutputStream.writeSignedLong(invocations);
	    }
	} catch (IOException e) {
	    System.err.println("WARN: Could not write time for '" + methodDefinition + "'.");
	    e.printStackTrace();
	}
    }

}
