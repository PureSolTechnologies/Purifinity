package com.puresoltechnologies.toolshed.agent.profiler.asm;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.puresoltechnologies.streaming.binary.BinaryOutputStream;
import com.puresoltechnologies.toolshed.agent.MethodDefinition;

public class ProfilerClassVisitor extends ClassVisitor {

    private final String className;
    private final Map<Short, MethodDefinition> methods;
    private final BinaryOutputStream idsOutputStream;
    private String internalClassName;
    private short methodId = 0;
    private boolean isInterface = false;

    public ProfilerClassVisitor(ClassWriter cw, String className, Map<Short, MethodDefinition> methods,
	    BinaryOutputStream idsOutputStream) {
	super(Opcodes.ASM6, cw);
	this.className = className;
	this.methods = methods;
	this.idsOutputStream = idsOutputStream;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
	super.visit(version, access, name, signature, superName, interfaces);
	this.internalClassName = name;
	if ((access & Opcodes.ACC_INTERFACE) == Opcodes.ACC_INTERFACE) {
	    /*
	     * We check for interface, because for this, we cannot add variables for time
	     * and invocations.
	     */
	    isInterface = true;
	}
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
	    String[] exceptions) {
	if (isInterface) {
	    System.out.println("Skipping interface method: " + internalClassName + "#" + name + descriptor);
	    return super.visitMethod(access, name, descriptor, signature, exceptions);
	}
	if (((access & Opcodes.ACC_ABSTRACT) == Opcodes.ACC_ABSTRACT) //
		|| ((access & Opcodes.ACC_NATIVE) == Opcodes.ACC_ABSTRACT) //
	) {
	    System.out.println("Skipping abstract or native method: " + internalClassName + "#" + name + descriptor);
	    return super.visitMethod(access, name, descriptor, signature, exceptions);
	}
	try {
	    methodId++;
	    methods.put(methodId, new MethodDefinition(className.replaceAll("/", "."), name, descriptor));
	    System.out.println(
		    "Instrumenting method: " + internalClassName + "#" + name + descriptor + ", id=" + methodId);
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
	idsOutputStream.writeNulTerminatedString(methodName, Charset.defaultCharset());
	idsOutputStream.writeNulTerminatedString(descriptor, Charset.defaultCharset());
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
