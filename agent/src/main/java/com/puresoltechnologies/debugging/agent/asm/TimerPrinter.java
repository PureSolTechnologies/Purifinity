package com.puresoltechnologies.debugging.agent.asm;

public class TimerPrinter {

    public static void printStartTime(Thread thread, int classId, short methodId, long start) {
	System.err.println("Thread '" + thread.getName() + "': start " + classId + "/" + methodId + "=" + start);
    }

    public static void printEndTime(Thread thread, int classId, short methodId, long end) {
	System.err.println("Thread '" + thread.getName() + "': end " + classId + "/" + methodId + "=" + end);
    }

}
