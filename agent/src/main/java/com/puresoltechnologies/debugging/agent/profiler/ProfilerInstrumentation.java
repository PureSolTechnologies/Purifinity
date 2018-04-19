package com.puresoltechnologies.debugging.agent.profiler;

import static org.objectweb.asm.ClassReader.EXPAND_FRAMES;
import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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

    private final File idsFile;
    private final BinaryOutputStream idsOutputStream;
    private int classId = 0;
    private final Map<Integer, String> classes = new HashMap<>();
    private final Map<Integer, Map<Short, String>> methods = new HashMap<>();

    public ProfilerInstrumentation() throws IOException {
	File outputDirectory = Configuration.getOutputDirectory();
	idsFile = new File(outputDirectory, "ids");
	ProfileWriter.initialize();
	idsOutputStream = new BinaryOutputStream(new OptimizedFileOutputStream(idsFile), ByteOrder.LITTLE_ENDIAN);
    }

    @Override
    public void close() throws IOException {
	idsOutputStream.close();
	try {
	    for (Entry<Integer, String> entry : classes.entrySet()) {
		int id = entry.getKey();
		Class<?> clazz = Class.forName(entry.getValue().replaceAll("/", "."));
		for (Entry<Short, String> methodEntry : methods.get(entry.getKey()).entrySet()) {
		    short methodId = methodEntry.getKey();
		    try {
			Field totalTimeField = clazz.getDeclaredField("total_time_" + methodId + "_");
			Field invocationsField = clazz.getDeclaredField("invocations_" + methodId + "_");
			try {
			    totalTimeField.setAccessible(true);
			    invocationsField.setAccessible(true);
			    long time = totalTimeField.getLong(null);
			    long invocations = invocationsField.getLong(null);
			    ProfileWriter.printTime(clazz, methods.get(id).get(methodId), time, invocations);
			    methodId++;
			    totalTimeField = clazz.getDeclaredField("total_time_" + methodId + "_");
			    invocationsField = clazz.getDeclaredField("invocations_" + methodId + "_");
			} catch (InaccessibleObjectException e) {
			    // intentionally left empty
			}
		    } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			// intentionally left empty as abort criterion
		    }
		}
	    }
	} catch (ClassNotFoundException | SecurityException e) {
	    e.printStackTrace();
	}
	ProfileWriter.shutdown();
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
	    ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
	try {
	    if (Configuration.hasFilterPattern()) {
		if (!Configuration.getFilterPattern().matcher(className).matches()) {
		    System.out.println("Skipping class: " + className);
		    return null;
		}
	    }
	    classId++;
	    methods.put(classId, new HashMap<>());
	    classes.put(classId, className);
	    System.out.println("Instrumenting class: " + className + ", id=" + classId);
	    writeClassIdMapping(className);
	    ClassReader cr = new ClassReader(classfileBuffer);
	    ClassWriter cw = new ClassWriter(COMPUTE_FRAMES);
	    ProfilerClassVisitor cv = new ProfilerClassVisitor(cw, classId, methods.get(classId), idsOutputStream);
	    cr.accept(cv, EXPAND_FRAMES);
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
