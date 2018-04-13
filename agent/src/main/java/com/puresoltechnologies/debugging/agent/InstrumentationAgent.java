package com.puresoltechnologies.debugging.agent;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

/**
 * This agent is used to run the instrumentation process.
 * 
 * @author Rick-Rainer Ludwig
 */
public class InstrumentationAgent {

    public static void premain(String args, Instrumentation instrumentation) {
	Configuration.initialize(args);
	try {
	    ProfilerInstrumentation transformer = new ProfilerInstrumentation();
	    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
		try {
		    transformer.close();
		} catch (IOException e) {
		    // intentionally left empty
		}
	    }));
	    instrumentation.addTransformer(transformer);
	} catch (IOException e) {
	    throw new RuntimeException("Cannot instrument for classes.", e);
	}
    }
}
