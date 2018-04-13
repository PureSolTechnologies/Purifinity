package com.puresoltechnologies.debugging.agent;

import java.lang.instrument.Instrumentation;

/**
 * This agent is used to run the instrumentation process.
 * 
 * @author Rick-Rainer Ludwig
 */
public class InstrumentationAgent {

    public static void premain(String args, Instrumentation instrumentation) {
	Configuration.initialize(args);
	ProfilerInstrumentation transformer = new ProfilerInstrumentation();
	instrumentation.addTransformer(transformer);
    }
}
