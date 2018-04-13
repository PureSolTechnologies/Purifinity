package com.puresoltechnologies.debugging.agent;

import java.lang.instrument.Instrumentation;

public class InstrumentationAgent {

    public static void premain(String args, Instrumentation instrumentation) {
	System.out.println("args: " + args);
	ProfilingInstrumentationTransformer transformer = new ProfilingInstrumentationTransformer();
	instrumentation.addTransformer(transformer);
    }
}
