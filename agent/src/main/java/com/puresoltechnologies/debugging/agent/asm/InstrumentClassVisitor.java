package com.puresoltechnologies.debugging.agent.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class InstrumentClassVisitor extends ClassVisitor {

    private String className = null;

    public InstrumentClassVisitor(ClassVisitor cv) {
	super(Opcodes.ASM6, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
	super.visit(version, access, name, signature, superName, interfaces);
	this.className = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
	    String[] exceptions) {
	MethodVisitor visitMethod = super.visitMethod(access, name, descriptor, signature, exceptions);
	if (name.equals("test")) {
	    System.out.println("test() found.");
	    return new InstrumentMethodVisitor(this.className, access, descriptor, visitMethod);
	}
	return visitMethod;
    }
}
