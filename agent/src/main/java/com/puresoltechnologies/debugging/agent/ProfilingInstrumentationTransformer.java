package com.puresoltechnologies.debugging.agent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;

public class ProfilingInstrumentationTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
	    ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
	try {
	    if (!className.equals("com/puresoltechnologies/debugging/test/AgentTest")) {
		return null;
	    }
	    System.out.println("Instrumenting: " + className);
	    ClassPool classPool = new ClassPool();
	    classPool.appendClassPath(new LoaderClassPath(loader));
	    CtClass c = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
	    for (CtMethod declaredMethod : c.getDeclaredMethods()) {
		declaredMethod.addLocalVariable("start", CtClass.longType);
		declaredMethod.insertBefore("start = System.nanoTime();");
		declaredMethod.insertAfter(
			"long stop = System.nanoTime(); System.out.println(String.valueOf((stop - start) / 1000)+ \"us\");");
	    }
	    return c.toBytecode();
	} catch (CannotCompileException | IOException | RuntimeException e) {
	    throw new RuntimeException(e);
	}
    }
}
