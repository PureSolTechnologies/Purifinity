package com.puresoltechnologies.debugging.agent.profiler.asm;

import java.io.IOException;
import java.nio.charset.Charset;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.puresoltechnologies.streaming.binary.BinaryOutputStream;

public class ProfilerClassVisitor extends ClassVisitor {

    private final int classId;
    private final BinaryOutputStream idsOutputStream;
    private String internalClassName;
    private short methodId = 0;

    public ProfilerClassVisitor(ClassVisitor cv, int classId, BinaryOutputStream idsOutputStream) {
	super(Opcodes.ASM6, cv);
	this.classId = classId;
	this.idsOutputStream = idsOutputStream;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
	super.visit(version, access, name, signature, superName, interfaces);
	this.internalClassName = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
	    String[] exceptions) {
	try {
	    methodId++;
	    System.out.println("Instrumenting method: " + name + descriptor + ", id=" + methodId);
	    writeMethodIdMapping(name, descriptor);
	    MethodVisitor visitMethod = super.visitMethod(access, name, descriptor, signature, exceptions);
	    return new ProfilerMethodVisitor(classId, methodId, internalClassName, access, descriptor, visitMethod);
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
	for (int i = 1; i <= methodId; i++) {
	    cv.visitField(Opcodes.ACC_STATIC, "total_time_" + i + "_", "J", null, 0L);
	    cv.visitField(Opcodes.ACC_STATIC, "invocations_" + i + "_", "J", null, 0L);
	}
	super.visitEnd();
    }
}
