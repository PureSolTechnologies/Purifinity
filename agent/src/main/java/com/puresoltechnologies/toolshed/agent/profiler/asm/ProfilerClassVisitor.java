package com.puresoltechnologies.toolshed.agent.profiler.asm;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.puresoltechnologies.streaming.binary.BinaryOutputStream;

public class ProfilerClassVisitor extends ClassVisitor {

    private final Map<Short, String> methods;
    private final BinaryOutputStream idsOutputStream;
    private String internalClassName;
    private short methodId = 0;
    private boolean isInterface = false;

    public ProfilerClassVisitor(ClassWriter cw, Map<Short, String> methods, BinaryOutputStream idsOutputStream) {
	super(Opcodes.ASM6, cw);
	this.methods = methods;
	this.idsOutputStream = idsOutputStream;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
	super.visit(version, access, name, signature, superName, interfaces);
	this.internalClassName = name;
	if ((access & Opcodes.ACC_INTERFACE) == Opcodes.ACC_INTERFACE) {
	    isInterface = true;
	}
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
	    String[] exceptions) {
	if (isInterface) {
	    return super.visitMethod(access, name, descriptor, signature, exceptions);
	}
	try {
	    methodId++;
	    methods.put(methodId, name + descriptor);
	    System.out.println("Instrumenting method: " + name + descriptor + ", id=" + methodId);
	    writeMethodIdMapping(name, descriptor);
	    MethodVisitor visitMethod = super.visitMethod(access, name, descriptor, signature, exceptions);
	    return new ProfilerMethodVisitor(methodId, internalClassName, access, descriptor, visitMethod,
		    idsOutputStream);
	} catch (IOException e) {
	    throw new RuntimeException("Could not instrument method '" + name + "'.", e);
	}
    }

    private void writeMethodIdMapping(String methodName, String descriptor) throws IOException {
	idsOutputStream.writeUnsignedByte(1);
	idsOutputStream.writeNulTerminatedString(methodName + descriptor, Charset.defaultCharset());
	idsOutputStream.writeSignedShort(methodId);
    }

    @Override
    public void visitEnd() {
	if (!isInterface) {
	    for (int i = 1; i <= methodId; i++) {
		cv.visitField(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_SYNTHETIC, "total_time_" + i + "_",
			"J", null, 0L);
		cv.visitField(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_SYNTHETIC, "invocations_" + i + "_",
			"J", null, 0L);
	    }
	}
	super.visitEnd();
    }
}
