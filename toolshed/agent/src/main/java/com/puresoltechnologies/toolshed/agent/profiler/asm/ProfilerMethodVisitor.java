package com.puresoltechnologies.toolshed.agent.profiler.asm;

import static org.objectweb.asm.Opcodes.ASM6;
import static org.objectweb.asm.Opcodes.ATHROW;
import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.RETURN;

import java.io.IOException;
import java.nio.charset.Charset;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.puresoltechnologies.streaming.binary.BinaryOutputStream;
import com.puresoltechnologies.toolshed.agent.Logging;
import com.puresoltechnologies.toolshed.agent.asm.LocalVariablesSorter;

public class ProfilerMethodVisitor extends LocalVariablesSorter implements Logging {

    private final Label startMethodScope = new Label();
    private final Label endMethodScope = new Label();
    private int totalTimeIndex;
    private int selfTimeIndex;

    private final short methodId;
    private final String owner;
    private final int access;
    private final String method;
    private final String descriptor;
    private final BinaryOutputStream idsOutputStream;
    private final String totalTimeVariable;
    private final String selfTimeVariable;
    private final String invocationsVariable;

    public ProfilerMethodVisitor(short methodId, String owner, int access, String method, String descriptor,
	    MethodVisitor mv, BinaryOutputStream idsOutputStream) {
	super(ASM6, access, descriptor, mv);
	this.methodId = methodId;
	this.owner = owner;
	this.access = access;
	this.method = method;
	this.descriptor = descriptor;
	this.idsOutputStream = idsOutputStream;
	totalTimeVariable = "total_time_" + methodId + "_";
	selfTimeVariable = "self_time_" + methodId + "_";
	invocationsVariable = "invocations_" + methodId + "_";
    }

    @Override
    public void visitCode() {
	super.visitCode();
	if (((access & Opcodes.ACC_ABSTRACT) == Opcodes.ACC_ABSTRACT) //
		|| ((access & Opcodes.ACC_NATIVE) == Opcodes.ACC_ABSTRACT) //
	) {
	    logDebug("Skipping abstract or native method: " + owner + "#" + method + descriptor);
	    return;
	}
	// create method start label
	mv.visitLabel(startMethodScope);
	/*
	 * long totalTime;
	 */
	totalTimeIndex = newLocal(Type.getType("J"));
	mv.visitLocalVariable("totalTime", "J", null, startMethodScope, endMethodScope, totalTimeIndex);
	/*
	 * long selfTime;
	 */
	selfTimeIndex = newLocal(Type.getType("J"));
	mv.visitLocalVariable("selfTime", "J", null, startMethodScope, endMethodScope, selfTimeIndex);
	/*
	 * totalTime = java.lang.System.nanoTime();
	 */
	mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
	mv.visitInsn(Opcodes.DUP2);
	mv.visitVarInsn(Opcodes.LSTORE, totalTimeIndex);
	/*
	 * long selfTime = totalTime;
	 */
	mv.visitVarInsn(Opcodes.LSTORE, selfTimeIndex);
    }

    @Override
    public void visitInsn(int opcode) {
	if (((opcode >= IRETURN) && (opcode <= RETURN)) //
		|| (opcode == ATHROW)) {
	    /*
	     * totalTimeVariable += java.lang.System.nanoTime() - totalTimeIndex
	     */
	    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
	    mv.visitInsn(Opcodes.DUP2); // Duplicate value for selfTime
	    mv.visitVarInsn(Opcodes.LLOAD, totalTimeIndex);
	    mv.visitInsn(Opcodes.LSUB);
	    mv.visitFieldInsn(Opcodes.GETSTATIC, owner, totalTimeVariable, "J");
	    mv.visitInsn(Opcodes.LADD);
	    mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, totalTimeVariable, "J");
	    /*
	     * selfTimeVariable += java.lang.System.nanoTime()(duplicated) - selfTimeIndex
	     */
	    mv.visitVarInsn(Opcodes.LLOAD, selfTimeIndex);
	    mv.visitInsn(Opcodes.LSUB);
	    mv.visitFieldInsn(Opcodes.GETSTATIC, owner, selfTimeVariable, "J");
	    mv.visitInsn(Opcodes.LADD);
	    mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, selfTimeVariable, "J");
	    /*
	     * invocationsVariable += 1
	     */
	    mv.visitFieldInsn(Opcodes.GETSTATIC, owner, invocationsVariable, "J");
	    mv.visitLdcInsn(1l);
	    mv.visitInsn(Opcodes.LADD);
	    mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, invocationsVariable, "J");
	}
	super.visitInsn(opcode);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
	try {
	    writeInvokation(owner, name, descriptor);
	    /*
	     * selfTimeVariable += java.lang.System.nanoTime() - selfTime
	     */
	    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
	    mv.visitVarInsn(Opcodes.LLOAD, selfTimeIndex);
	    mv.visitInsn(Opcodes.LSUB);
	    mv.visitFieldInsn(Opcodes.GETSTATIC, this.owner, selfTimeVariable, "J");
	    mv.visitInsn(Opcodes.LADD);
	    mv.visitFieldInsn(Opcodes.PUTSTATIC, this.owner, selfTimeVariable, "J");
	    /*
	     * Invoke method normally
	     */
	    super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
	    /*
	     * selfTime = java.lang.System.nanoTime()
	     */
	    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
	    mv.visitVarInsn(Opcodes.LSTORE, selfTimeIndex);
	} catch (IOException e) {
	    throw new RuntimeException("Could not instrument method '" + name + "'.", e);
	}
    }

    private void writeInvokation(String owner, String methodName, String descriptor) throws IOException {
	Charset defaultCharset = Charset.defaultCharset();
	logTrace("Invokes " + owner + "#" + methodName + descriptor);
	idsOutputStream.writeUnsignedByte(3);
	idsOutputStream.writeNulTerminatedString(owner.replaceAll("/", "."), defaultCharset);
	idsOutputStream.writeNulTerminatedString(methodName, defaultCharset);
	idsOutputStream.writeNulTerminatedString(descriptor.replaceAll("/", "."), defaultCharset);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
	// create method end label
	mv.visitLabel(endMethodScope);
	super.visitMaxs(maxStack, maxLocals);
    }

}
