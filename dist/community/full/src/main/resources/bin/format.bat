@echo off

set BASEDIR=%cd%
echo "Base directory: " %BASEDIR%

set HADOOP_HOME=%BASEDIR%\..\lib\hadoop-2.7.2
set HADOOP_BIN=%HADOOP_HOME%\bin
echo "Hadoop home: " %HADOOP_HOME%

echo "Formatting Hadoop Distributed Filesystem 2.7.2..."
%HADOOP_BIN%/hadoop.cmd namenode -format
