package com.puresoltechnologies.debugging.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import com.puresoltechnologies.debugging.agent.ByteCodeCassLoader;
import com.puresoltechnologies.debugging.agent.asm.ClassPrinter;
import com.puresoltechnologies.debugging.agent.asm.InstrumentClassVisitor;

public class AsmTest {

    @Test
    public void test() throws IOException, InterruptedException {
	ClassPrinter cp = new ClassPrinter();
	ClassReader cr = new ClassReader(AsmTest.class.getName());
	cr.accept(cp, 0);
	Thread.sleep(100);
    }

    @Test
    public void test2() throws Exception {
	ClassWriter cw = new ClassWriter(0);
	InstrumentClassVisitor cv = new InstrumentClassVisitor(cw, 1);
	ClassReader cr = new ClassReader(AsmTest.class.getName());
	cr.accept(cv, 0);
	byte[] byteCode = cw.toByteArray();
	assertNotNull(byteCode);

	ByteCodeCassLoader cl = new ByteCodeCassLoader();
	Class<?> clazz = cl.defineClass("com.puresoltechnologies.debugging.test.AsmTest", byteCode);
	Object instance = clazz.getConstructor().newInstance();
	Method testMethod = clazz.getMethod("test");
	testMethod.invoke(instance);
    }

}
