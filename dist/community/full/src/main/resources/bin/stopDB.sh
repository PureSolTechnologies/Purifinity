#!/bin/sh
BASEDIR=$(dirname $PWD/$0)
echo "Base directory: " $BASEDIR

HADOOP_HOME=$BASEDIR/../lib/hadoop-${hadoop.version}
HADOOP_SBIN=$HADOOP_HOME/sbin
echo "Hadoop home: " $HADOOP_HOME

HBASE_HOME=$BASEDIR/../lib/hbase-${hbase.version}
HBASE_BIN=$HBASE_HOME/bin
echo "HBase home: " $HBASE_HOME

echo "Stopping HBase ${hbase.version}..."
cd $HBASE_BIN && sh stop-hbase.sh

echo "Stopping Hadoop MapReduce History Daemon ${hadoop.version}..."
cd $HADOOP_SBIN && sh mr-jobhistory-daemon.sh stop historyserver

echo "Stopping Hadoop Yarn ${hadoop.version}..."
cd $HADOOP_SBIN && sh stop-yarn.sh

echo "Stopping Hadoop Distributed Filesystem ${hadoop.version}..."
cd $HADOOP_SBIN && sh stop-dfs.sh
