#!/bin/sh
BASEDIR=$(dirname $PWD/$0)
echo "Base directory: " $BASEDIR

HADOOP_HOME=$BASEDIR/../lib/hadoop-${hadoop.version}
HADOOP_SBIN=$HADOOP_HOME/sbin
echo "Hadoop home: " $HADOOP_HOME

HBASE_HOME=$BASEDIR/../lib/hbase-${hbase.version}
HBASE_BIN=$HBASE_HOME/bin
echo "HBase home: " $HBASE_HOME

echo "Starting Hadoop Distributed Filesystem ${hadoop.version}..."
$HADOOP_SBIN/start-dfs.sh

echo "Starting Hadoop Yarn ${hadoop.version}..."
$HADOOP_SBIN/start-yarn.sh

echo "Starting Hadoop MapReduce History Daemon ${hadoop.version}..."
$HADOOP_SBIN/mr-jobhistory-daemon.sh start historyserver

echo "Starting HBase ${hbase.version}..."
$HBASE_BIN/start-hbase.sh
