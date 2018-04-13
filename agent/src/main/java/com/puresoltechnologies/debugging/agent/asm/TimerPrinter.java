package com.puresoltechnologies.debugging.agent.asm;

public class TimerPrinter {

    public static void printTime(long start, long end) {
	System.err.println("time: " + (end - start) + "ms");
    }

}
