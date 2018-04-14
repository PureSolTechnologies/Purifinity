package com.puresoltechnologies.debugging.agent;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

import com.puresoltechnologies.debugging.agent.profiler.ProfileWriter;
import com.puresoltechnologies.debugging.agent.profiler.ProfilerInstrumentation;

/**
 * This agent is used to run the profiler instrumentation process.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisAgent {

    public static void premain(String args, Instrumentation instrumentation) {
	Configuration.initialize(args);
	try {
	    ProfileWriter.initialize();
	    ProfilerInstrumentation transformer = new ProfilerInstrumentation();
	    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
		try {
		    transformer.close();
		    ProfileWriter.shutdown();
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
