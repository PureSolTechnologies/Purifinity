#!/bin/sh

# Calculate TOOLSHED_HOME
TOOLSHED_HOME="$( cd "$( dirname $0 )/.." && pwd )"

# JVM arguments...
JVM_ARGS="-Dtoolshed.home=$TOOLSHED_HOME"
# Debug arguments 
#JVM_ARGS="$JVM_ARGS -agentlib:jdwp=transport=dt_socket,server=y,address=8000,suspend=y" 
#JVM_ARGS="$JVM_ARGS -javaagent:${TOOLSHED_HOME}/agents/profiler-${project.version}.jar"

# Running ToolShed client...
java $JVM_ARGS -classpath "$TOOLSHED_HOME/lib/*" com.puresoltechnologies.toolshed.client.ToolShedApplication
