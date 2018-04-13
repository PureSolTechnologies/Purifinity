package com.puresoltechnologies.debugging.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import com.puresoltechnologies.debugging.agent.asm.InstrumentClassVisitor;

/**
 * This class instruments the configured classes for profiling.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ProfilerInstrumentation implements ClassFileTransformer {

    private int classId = 0;

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
	    ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
	try {
	    if (!className.equals("com/puresoltechnologies/debugging/test/AgentTest")) {
		return null;
	    }
	    System.out.println("Instrumenting: " + className + "id =" + classId);
	    ClassReader cr = new ClassReader(classfileBuffer);
	    ClassWriter cw = new ClassWriter(0);
	    InstrumentClassVisitor cv = new InstrumentClassVisitor(cw, classId);
	    cr.accept(cv, 0);
	    classId++;
	    return cw.toByteArray();
	} catch (RuntimeException e) {
	    throw new RuntimeException(e);
	}
    }
}
