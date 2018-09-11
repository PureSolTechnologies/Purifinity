package com.puresoltechnologies.toolshed.agent.profiler;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import com.puresoltechnologies.streaming.binary.BinaryOutputStream;
import com.puresoltechnologies.streaming.streams.OptimizedFileOutputStream;
import com.puresoltechnologies.toolshed.agent.Configuration;
import com.puresoltechnologies.toolshed.agent.Logging;
import com.puresoltechnologies.toolshed.agent.MethodDefinition;
import com.puresoltechnologies.toolshed.agent.profiler.asm.ProfilerClassVisitor;

/**
 * This class instruments the configured classes for profiling.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ProfilerInstrumentation implements ClassFileTransformer, Closeable, Logging {

    private final File idsFile;
    private final BinaryOutputStream idsOutputStream;
    private int classId = 0;
    private final Map<Integer, String> classes = new HashMap<>();
    private final Map<Integer, Map<Short, MethodDefinition>> methods = new HashMap<>();
    private boolean isClosing = false;
    private final ProfileWriter profileWriter;

    public ProfilerInstrumentation() throws IOException {
	File outputDirectory = Configuration.getOutputDirectory();
	idsFile = new File(outputDirectory, "ids");
	profileWriter = new ProfileWriter();
	idsOutputStream = new BinaryOutputStream(new OptimizedFileOutputStream(idsFile), ByteOrder.LITTLE_ENDIAN);
    }

    @Override
    public synchronized byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
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
	    if (className.startsWith("jdk/")) {
		return null;
	    }
	    classId++;
	    logDebug("Instrumenting class: " + className + " (id=" + classId + ")");
	    HashMap<Short, MethodDefinition> methodsHash = new HashMap<>();
	    ClassReader cr = new ClassReader(classfileBuffer);
	    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
	    ProfilerClassVisitor cv = new ProfilerClassVisitor(cw, className, classId, methodsHash, idsOutputStream);
	    cr.accept(cv, ClassReader.EXPAND_FRAMES);
	    classes.put(classId, className.replaceAll("/", "."));
	    methods.put(classId, methodsHash);
	    return cw.toByteArray();
	} catch (IOException e) {
	    logWarn("Could not instrument class '" + className + "'.", e);
	    return null;
	}
    }

    @Override
    public void close() {
	isClosing = true;
	try {
	    idsOutputStream.close();
	} catch (IOException e) {
	    logWarn("Could not close ids file.", e);
	}
	try {
	    for (Entry<Integer, String> entry : classes.entrySet()) {
		writeClassResults(entry);
	    }
	} catch (SecurityException e) {
	    logError("Could not close instrumentation.", e);
	}
	try {
	    profileWriter.close();
	} catch (IOException e) {
	    logWarn("Could not close profile file.", e);
	}
    }

    private void writeClassResults(Entry<Integer, String> entry) {
	String className = entry.getValue().replaceAll("/", ".");
	logDebug("Writing profile for class '" + className + "' (id=" + entry.getKey() + ")");
	try {
	    Class<?> clazz = Class.forName(className);
	    for (Entry<Short, MethodDefinition> methodEntry : methods.get(entry.getKey()).entrySet()) {
		short methodId = methodEntry.getKey();
		writeMethodResult(clazz, methodId, methodEntry.getValue());
	    }
	} catch (Exception | LinkageError e) {
	    logWarn("Could not write class results for '" + className + "'.", e);
	}
    }

    private void writeMethodResult(Class<?> clazz, short methodId, MethodDefinition methodEntry) {
	try {
	    Field totalTimeField = clazz.getDeclaredField("total_time_" + methodId + "_");
	    Field selfTimeField = clazz.getDeclaredField("self_time_" + methodId + "_");
	    Field invocationsField = clazz.getDeclaredField("invocations_" + methodId + "_");
	    totalTimeField.setAccessible(true);
	    invocationsField.setAccessible(true);
	    long totalTime = totalTimeField.getLong(null);
	    long selfTime = selfTimeField.getLong(null);
	    long invocations = invocationsField.getLong(null);
	    profileWriter.printTime(methodEntry, totalTime, selfTime, invocations);
	    methodId++;
	    totalTimeField = clazz.getDeclaredField("total_time_" + methodId + "_");
	    invocationsField = clazz.getDeclaredField("invocations_" + methodId + "_");
	} catch (NoSuchFieldException nsfe) {
	    logWarn("Could not write method results for '" + methodEntry
		    + "', because field was not found. Potentially a class used from agent.");
	} catch (Exception | LinkageError e) {
	    logWarn("Could not write method results for '" + methodEntry + "'.", e);
	}
    }
}
