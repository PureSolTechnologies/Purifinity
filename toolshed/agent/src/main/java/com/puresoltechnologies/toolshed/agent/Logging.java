package com.puresoltechnologies.toolshed.agent;

public interface Logging {

    public default void logTrace(String message) {
	if (AgentLogLevel.TRACE.ordinal() >= Configuration.getLogLevel().ordinal()) {
	    System.out.println("Trace: " + message);
	}
    }

    public default void logDebug(String message) {
	if (AgentLogLevel.DEBUG.ordinal() >= Configuration.getLogLevel().ordinal()) {
	    System.out.println("Debug: " + message);
	}
    }

    public default void logInfo(String message) {
	if (AgentLogLevel.INFO.ordinal() >= Configuration.getLogLevel().ordinal()) {
	    System.out.println("Info:  " + message);
	}
    }

    public default void logWarn(String message) {
	if (AgentLogLevel.WARN.ordinal() >= Configuration.getLogLevel().ordinal()) {
	    System.err.println("Warn:  " + message);
	}
    }

    public default void logWarn(String message, Throwable t) {
	if (AgentLogLevel.WARN.ordinal() >= Configuration.getLogLevel().ordinal()) {
	    System.err.println("Warn:  " + message);
	    t.printStackTrace();
	}
    }

    public default void logError(String message) {
	if (AgentLogLevel.ERROR.ordinal() >= Configuration.getLogLevel().ordinal()) {
	    System.err.println("Error: " + message);
	}
    }

    public default void logError(String message, Throwable t) {
	if (AgentLogLevel.ERROR.ordinal() >= Configuration.getLogLevel().ordinal()) {
	    System.err.println("Error: " + message);
	    t.printStackTrace();
	}
    }

}
