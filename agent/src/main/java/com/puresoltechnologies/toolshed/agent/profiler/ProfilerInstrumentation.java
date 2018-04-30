package com.puresoltechnologies.toolshed.agent.profiler;

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

import com.puresoltechnologies.streaming.binary.BinaryOutputStream;
import com.puresoltechnologies.streaming.streams.OptimizedFileOutputStream;
import com.puresoltechnologies.toolshed.agent.Configuration;
import com.puresoltechnologies.toolshed.agent.MethodDefinition;
import com.puresoltechnologies.toolshed.agent.profiler.asm.ProfilerClassVisitor;

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
    private final Map<Integer, Map<Short, MethodDefinition>> methods = new HashMap<>();
    private boolean isClosing = false;

    public ProfilerInstrumentation() throws IOException {
	File outputDirectory = Configuration.getOutputDirectory();
	idsFile = new File(outputDirectory, "ids");
	ProfileWriter.initialize();
	idsOutputStream = new BinaryOutputStream(new OptimizedFileOutputStream(idsFile), ByteOrder.LITTLE_ENDIAN);
    }

    @Override
    public void close() throws IOException {
	isClosing = true;
	idsOutputStream.close();
	try {
	    for (Entry<Integer, String> entry : classes.entrySet()) {
		writeClassResults(entry);
	    }
	} catch (ClassNotFoundException | SecurityException e) {
	    e.printStackTrace();
	}
	ProfileWriter.shutdown();
    }

    private void writeClassResults(Entry<Integer, String> entry) throws ClassNotFoundException {
	Class<?> clazz = Class.forName(entry.getValue().replaceAll("/", "."));
	for (Entry<Short, MethodDefinition> methodEntry : methods.get(entry.getKey()).entrySet()) {
	    short methodId = methodEntry.getKey();
	    writeMethodResult(clazz, methodId, methodEntry.getValue());
	}
    }

    private void writeMethodResult(Class<?> clazz, short methodId, MethodDefinition methodEntry) {
	try {
	    Field totalTimeField = clazz.getDeclaredField("total_time_" + methodId + "_");
	    Field invocationsField = clazz.getDeclaredField("invocations_" + methodId + "_");
	    try {
		totalTimeField.setAccessible(true);
		invocationsField.setAccessible(true);
		long time = totalTimeField.getLong(null);
		long invocations = invocationsField.getLong(null);
		ProfileWriter.printTime(methodEntry, time, invocations);
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

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
	    ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
	if (isClosing) {
	    return null;
	}
	try {
	    if (Configuration.hasFilterPattern()) {
		if (!Configuration.getFilterPattern().matcher(className).matches()) {
		    return null;
		}
	    }
	    if (className.startsWith("jdk/") //
		    || className.startsWith("sun/") //
		    || className.startsWith("com/sun/") //
		    || className.startsWith("javafx/beans/") //
		    || className.startsWith("javafx/scene/") //
		    || className.startsWith("javafx/css/") //
	    ) {
		return null;
	    }
	    classId++;
	    methods.put(classId, new HashMap<>());
	    classes.put(classId, className);
	    System.out.println("Instrumenting class: " + className + ", id=" + classId);
	    writeClassIdMapping(className);
	    ClassReader cr = new ClassReader(classfileBuffer);
	    ClassWriter cw = new ClassWriter(COMPUTE_FRAMES);
	    ProfilerClassVisitor cv = new ProfilerClassVisitor(cw, className, methods.get(classId), idsOutputStream);
	    cr.accept(cv, EXPAND_FRAMES);
	    return cw.toByteArray();
	} catch (IOException e) {
	    throw new RuntimeException("Could not instrument class '" + className + "'.", e);
	}
    }

    private void writeClassIdMapping(String className) throws IOException {
	idsOutputStream.writeUnsignedByte(0);
	idsOutputStream.writeNulTerminatedString(className.replaceAll("/", "."), Charset.defaultCharset());
	idsOutputStream.writeSignedInt(classId);
    }
}
