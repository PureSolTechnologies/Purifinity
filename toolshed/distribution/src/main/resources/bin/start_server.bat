@echo off

rem Calculate TOOLSHED_HOME
set TOOLSHED_HOME=%cd%\..

rem JVM arguments...
set JVM_ARGS=-Dtoolshed.home=%TOOLSHED_HOME% -classpath %TOOLSHED_HOME%\lib\*

rem Debug arguments 
rem set JVM_ARGS="%JVM_ARGS% -agentlib:jdwp=transport=dt_socket,server=y,address=8000,suspend=y" 
rem set JVM_ARGS="%JVM_ARGS% -javaagent:%TOOLSHED_HOME%\agents\profiler-${project.version}.jar"

rem Running ToolShed server...
java %JVM_ARGS% com.puresoltechnologies.toolshed.server.impl.ToolShedServer server %TOOLSHED_HOME%\etc\server.yml
