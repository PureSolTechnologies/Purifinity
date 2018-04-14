package com.puresoltechnologies.debugging.agent.profiler;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import com.puresoltechnologies.debugging.agent.Configuration;
import com.puresoltechnologies.debugging.agent.profiler.asm.ProfilerClassVisitor;
import com.puresoltechnologies.streaming.binary.BinaryOutputStream;
import com.puresoltechnologies.streaming.streams.OptimizedFileOutputStream;

/**
 * This class instruments the configured classes for profiling.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ProfilerInstrumentation implements ClassFileTransformer, Closeable {

    private final File outputDirectory;
    private final File idsFile;
    private final BinaryOutputStream idsOutputStream;
    private int classId = 0;

    public ProfilerInstrumentation() throws IOException {
	File directory = Configuration.getDirectory();
	File profileDirectory = new File(directory, "purifinity.profile");
	outputDirectory = new File(profileDirectory, String.valueOf(System.currentTimeMillis()));
	if (!outputDirectory.exists()) {
	    if (!outputDirectory.mkdirs()) {
		throw new IOException("Cannot create directory '" + outputDirectory + "'.");
	    }
	} else if (!outputDirectory.isDirectory()) {
	    throw new IOException("Output directory '" + outputDirectory + "' is not a directory.");
	}
	System.out.println("Output directory: " + outputDirectory);
	idsFile = new File(outputDirectory, "ids");
	idsOutputStream = new BinaryOutputStream(new OptimizedFileOutputStream(idsFile), ByteOrder.LITTLE_ENDIAN);
    }

    @Override
    public void close() throws IOException {
	idsOutputStream.close();
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
	    ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
	try {
	    if (!className.equals("com/puresoltechnologies/debugging/test/AgentTest")) {
		return null;
	    }
	    System.out.println("Instrumenting class: " + className + ", id=" + classId);
	    writeClassIdMapping(className);
	    ClassReader cr = new ClassReader(classfileBuffer);
	    ClassWriter cw = new ClassWriter(0);
	    ProfilerClassVisitor cv = new ProfilerClassVisitor(cw, classId, idsOutputStream);
	    cr.accept(cv, 0);
	    classId++;
	    return cw.toByteArray();
	} catch (IOException e) {
	    throw new RuntimeException("Could not instrument class '" + className + "'.", e);
	}
    }

    private void writeClassIdMapping(String className) throws IOException {
	idsOutputStream.writeUnsignedByte(0);
	idsOutputStream.writeNulTerminatedString(className, Charset.defaultCharset());
	idsOutputStream.writeSignedInt(classId);
    }
}
