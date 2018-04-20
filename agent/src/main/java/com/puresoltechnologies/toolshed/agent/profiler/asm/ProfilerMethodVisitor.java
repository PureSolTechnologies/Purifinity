package com.puresoltechnologies.toolshed.agent.profiler.asm;

import static org.objectweb.asm.Opcodes.ASM6;
import static org.objectweb.asm.Opcodes.ATHROW;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.PUTSTATIC;
import static org.objectweb.asm.Opcodes.RETURN;

import java.io.IOException;
import java.nio.charset.Charset;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.puresoltechnologies.streaming.binary.BinaryOutputStream;
import com.puresoltechnologies.toolshed.agent.asm.LocalVariablesSorter;

public class ProfilerMethodVisitor extends LocalVariablesSorter {

    private final int classId;
    private final short methodId;
    private final String owner;
    private final BinaryOutputStream idsOutputStream;

    public ProfilerMethodVisitor(int classId, short methodId, String owner, int access, String descriptor,
	    MethodVisitor mv, BinaryOutputStream idsOutputStream) {
	super(ASM6, access, descriptor, mv);
	this.classId = classId;
	this.methodId = methodId;
	this.owner = owner;
	this.idsOutputStream = idsOutputStream;
    }

    @Override
    public void visitCode() {
	super.visitCode();
	mv.visitFieldInsn(GETSTATIC, owner, "total_time_" + methodId + "_", "J");
	mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
	mv.visitInsn(Opcodes.LSUB);
	mv.visitFieldInsn(PUTSTATIC, owner, "total_time_" + methodId + "_", "J");
	mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "invocations_" + methodId + "_", "J");
	mv.visitLdcInsn(1l);
	mv.visitInsn(Opcodes.LADD);
	mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "invocations_" + methodId + "_", "J");
    }

    @Override
    public void visitInsn(int opcode) {
	if (((opcode >= IRETURN) && (opcode <= RETURN)) //
		|| (opcode == ATHROW)) {
	    mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "total_time_" + methodId + "_", "J");
	    mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
	    mv.visitInsn(Opcodes.LADD);
	    mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "total_time_" + methodId + "_", "J");
	    //
	    // mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread",
	    // "()Ljava/lang/Thread;", false);
	    // mv.visitLdcInsn(classId);
	    // mv.visitLdcInsn(methodId);
	    // mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "total_time_" + methodId + "_",
	    // "J");
	    // mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "invocations_" + methodId + "_",
	    // "J");
	    // mv.visitMethodInsn(Opcodes.INVOKESTATIC,
	    // "com/puresoltechnologies/debugging/agent/profiler/ProfileWriter",
	    // "printTime", "(Ljava/lang/Thread;ISJJ)V", false);
	}
	mv.visitInsn(opcode);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
	try {
	    writeInvokation(owner, name, descriptor);
	    super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
	} catch (IOException e) {
	    throw new RuntimeException("Could not instrument method '" + name + "'.", e);
	}
    }

    private void writeInvokation(String owner, String methodName, String descriptor) throws IOException {
	idsOutputStream.writeUnsignedByte(2);
	idsOutputStream.writeNulTerminatedString(owner + ":" + methodName + descriptor, Charset.defaultCharset());
	idsOutputStream.writeSignedShort(methodId);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
	super.visitMaxs(maxStack + 7, maxLocals);
    }

}
