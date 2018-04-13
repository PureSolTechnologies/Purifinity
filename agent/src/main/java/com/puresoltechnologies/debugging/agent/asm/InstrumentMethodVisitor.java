package com.puresoltechnologies.debugging.agent.asm;

import static org.objectweb.asm.Opcodes.ASM6;
import static org.objectweb.asm.Opcodes.ATHROW;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.RETURN;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class InstrumentMethodVisitor extends LocalVariablesSorter {

    private final String owner;
    private final Label startMethodScope = new Label();
    private final Label endMethodScope = new Label();
    private int startTimeIndex;
    private int endTimeIndex;

    public InstrumentMethodVisitor(String owner, int access, String descriptor, MethodVisitor mv) {
	super(ASM6, 0, descriptor, mv);
	this.owner = owner;
    }

    @Override
    public void visitCode() {
	super.visitCode();
	mv.visitLabel(startMethodScope);
	startTimeIndex = newLocal(Type.getType("J"));
	mv.visitLocalVariable("startTime", "J", null, startMethodScope, endMethodScope, startTimeIndex);
	mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
	mv.visitVarInsn(Opcodes.LSTORE, startTimeIndex);
    }

    @Override
    public void visitInsn(int opcode) {
	if (((opcode >= IRETURN) && (opcode <= RETURN)) //
		|| (opcode == ATHROW)) {
	    endTimeIndex = newLocal(Type.getType("J"));
	    mv.visitLocalVariable("endTime", "J", null, startMethodScope, endMethodScope, endTimeIndex);
	    mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
	    mv.visitVarInsn(Opcodes.LSTORE, endTimeIndex);

	    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
	    mv.visitLdcInsn("1");
	    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

	    mv.visitVarInsn(Opcodes.LLOAD, startTimeIndex);
	    mv.visitVarInsn(Opcodes.LLOAD, endTimeIndex);
	    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/puresoltechnologies/debugging/agent/asm/TimerPrinter",
		    "printTime", "(JJ)V", false);

	}
	mv.visitInsn(opcode);
    }

    @Override
    public void visitMaxs(int stack, int locals) {
	mv.visitLabel(endMethodScope);
	super.visitMaxs(stack + 1, locals);
    }
}
