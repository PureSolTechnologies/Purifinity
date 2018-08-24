@echo off

set BASEDIR=%cd%
echo "Base directory: " %BASEDIR%

set HADOOP_HOME=%BASEDIR%\..\lib\hadoop-${hadoop.version}
set HADOOP_SBIN=%HADOOP_HOME%\sbin
echo "Hadoop home: " %HADOOP_HOME%

set HBASE_HOME=%BASEDIR%\..\lib\hbase-${hbase.version}
set HBASE_BIN=%HBASE_HOME%/bin
echo "HBase home: " %HBASE_HOME%

echo "Starting Hadoop Distributed Filesystem ${hadoop.version}..."
%HADOOP_SBIN%\start-dfs.cmd

echo "Starting Hadoop Yarn ${hadoop.version}..."
%HADOOP_SBIN%\start-yarn.cmd

echo "Starting Hadoop MapReduce History Daemon ${hadoop.version}..."
%HADOOP_SBIN%\mr-jobhistory-daemon.cmd start historyserver

echo "Starting HBase ${hbase.version}..."
%HBASE_BIN%\start-hbase.cm
