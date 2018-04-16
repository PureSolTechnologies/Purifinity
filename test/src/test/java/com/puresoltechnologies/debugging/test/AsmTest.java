package com.puresoltechnologies.debugging.test;

import static java.nio.ByteOrder.LITTLE_ENDIAN;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.objectweb.asm.ClassReader.EXPAND_FRAMES;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import com.puresoltechnologies.debugging.agent.ByteCodeCassLoader;
import com.puresoltechnologies.debugging.agent.Configuration;
import com.puresoltechnologies.debugging.agent.asm.ClassPrinter;
import com.puresoltechnologies.debugging.agent.profiler.ProfileWriter;
import com.puresoltechnologies.debugging.agent.profiler.asm.ProfilerClassVisitor;
import com.puresoltechnologies.streaming.binary.BinaryOutputStream;

public class AsmTest {

    @BeforeAll
    public static void initialize() throws FileNotFoundException {
	Configuration.initialize("");
	ProfileWriter.initialize();
    }

    @AfterAll
    public static void shutdown() throws IOException {
	ProfileWriter.shutdown();
    }

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
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	ProfilerClassVisitor cv = new ProfilerClassVisitor(cw, 1, new BinaryOutputStream(outputStream, LITTLE_ENDIAN));
	ClassReader cr = new ClassReader(AsmTest.class.getName());
	cr.accept(cv, EXPAND_FRAMES);
	byte[] byteCode = cw.toByteArray();
	assertNotNull(byteCode);

	ByteCodeCassLoader cl = new ByteCodeCassLoader();
	Class<?> clazz = cl.defineClass("com.puresoltechnologies.debugging.test.AsmTest", byteCode);

	for (Field field : clazz.getDeclaredFields()) {
	    System.out.println(field.getName());
	}

	Object instance = clazz.getConstructor().newInstance();
	Method testMethod = clazz.getMethod("test");
	testMethod.invoke(instance);
	System.out.println(outputStream.toString());
    }

}
