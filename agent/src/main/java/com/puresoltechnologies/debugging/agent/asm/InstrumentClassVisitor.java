package com.puresoltechnologies.debugging.agent.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class InstrumentClassVisitor extends ClassVisitor {

    private final int classId;
    private short methodId = 0;

    public InstrumentClassVisitor(ClassVisitor cv, int classId) {
	super(Opcodes.ASM6, cv);
	this.classId = classId;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
	    String[] exceptions) {
	MethodVisitor visitMethod = super.visitMethod(access, name, descriptor, signature, exceptions);
	if (name.equals("test")) {
	    System.out.println("test() found.");
	    return new InstrumentMethodVisitor(classId, methodId, access, descriptor, visitMethod);
	}
	methodId++;
	return visitMethod;
    }
}
