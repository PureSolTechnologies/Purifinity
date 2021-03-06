package com.puresoltechnologies.toolshed.agent.profiler;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

import com.puresoltechnologies.streaming.binary.BinaryOutputStream;
import com.puresoltechnologies.streaming.streams.OptimizedFileOutputStream;
import com.puresoltechnologies.toolshed.agent.Configuration;
import com.puresoltechnologies.toolshed.agent.Logging;
import com.puresoltechnologies.toolshed.agent.MethodDefinition;

public class ProfileWriter implements Closeable, Logging {

    private BinaryOutputStream binaryOutputStream = null;
    private Object lock = new Object();

    public ProfileWriter() throws FileNotFoundException {
	File outputDirectory = Configuration.getOutputDirectory();
	File profileFile = new File(outputDirectory, "profile.raw");
	binaryOutputStream = new BinaryOutputStream(new OptimizedFileOutputStream(profileFile),
		ByteOrder.LITTLE_ENDIAN);
    }

    @Override
    public void close() throws IOException {
	binaryOutputStream.flush();
	binaryOutputStream.close();
    }

    public void printTime(MethodDefinition methodDefinition, long totalTime, long selfTime, long invocations) {
	try {
	    synchronized (lock) {
		logTrace("write profile entry:" + methodDefinition.toString() + ": " + invocations + "x " + totalTime
			+ "ns");
		binaryOutputStream.write((byte) 1);
		Charset defaultCharset = Charset.defaultCharset();
		binaryOutputStream.writeNulTerminatedString(methodDefinition.getClassName(), defaultCharset);
		binaryOutputStream.writeNulTerminatedString(methodDefinition.getMethodName(), defaultCharset);
		binaryOutputStream.writeNulTerminatedString(methodDefinition.getDescriptor(), defaultCharset);
		binaryOutputStream.writeSignedLong(totalTime);
		binaryOutputStream.writeSignedLong(selfTime);
		binaryOutputStream.writeSignedLong(invocations);
	    }
	} catch (IOException e) {
	    logWarn("Could not write time for '" + methodDefinition + "'.", e);
	}
    }

}
