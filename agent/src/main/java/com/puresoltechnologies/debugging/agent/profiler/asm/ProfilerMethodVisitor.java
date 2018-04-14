package com.puresoltechnologies.debugging.agent.profiler.asm;

import static org.objectweb.asm.Opcodes.ASM6;
import static org.objectweb.asm.Opcodes.ATHROW;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.RETURN;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.puresoltechnologies.debugging.agent.asm.LocalVariablesSorter;

public class ProfilerMethodVisitor extends LocalVariablesSorter {

    private final int classId;
    private final short methodId;

    public ProfilerMethodVisitor(int classId, short methodId, int access, String descriptor, MethodVisitor mv) {
	super(ASM6, access, descriptor, mv);
	this.classId = classId;
	this.methodId = methodId;
    }

    @Override
    public void visitCode() {
	super.visitCode();
	mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
	mv.visitLdcInsn(classId);
	mv.visitLdcInsn(methodId);
	mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
	mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/puresoltechnologies/debugging/agent/profiler/ProfileWriter",
		"printStartTime", "(Ljava/lang/Thread;ISJ)V", false);
    }

    @Override
    public void visitInsn(int opcode) {
	if (((opcode >= IRETURN) && (opcode <= RETURN)) //
		|| (opcode == ATHROW)) {
	    mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
	    mv.visitLdcInsn(classId);
	    mv.visitLdcInsn(methodId);
	    mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
	    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/puresoltechnologies/debugging/agent/profiler/ProfileWriter",
		    "printEndTime", "(Ljava/lang/Thread;ISJ)V", false);
	}
	mv.visitInsn(opcode);
    }

    @Override
    public void visitMaxs(int stack, int locals) {
	super.visitMaxs(stack + 5, locals);
    }
}
