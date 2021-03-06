package com.puresoltechnologies.toolshed.agent;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

import com.puresoltechnologies.toolshed.agent.profiler.ProfilerInstrumentation;

/**
 * This agent is used to run the profiler instrumentation process.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisAgent {

    public static void premain(String args, Instrumentation instrumentation) {
	Configuration.initialize(args);
	try {
	    ProfilerInstrumentation transformer = new ProfilerInstrumentation();
	    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
		transformer.close();
	    }));
	    instrumentation.addTransformer(transformer);
	} catch (IOException e) {
	    throw new RuntimeException("Cannot instrument for classes.", e);
	}
    }
}
