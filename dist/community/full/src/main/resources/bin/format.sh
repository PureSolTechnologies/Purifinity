#!/bin/sh
BASEDIR=$(dirname $PWD/$0)
echo "Base directory: " $BASEDIR

HADOOP_HOME=$BASEDIR/../lib/hadoop-${hadoop.version}
HADOOP_SBIN=$HADOOP_HOME/sbin
echo "Hadoop home: " $HADOOP_HOME

echo "Formatting Hadoop Distributed Filesystem ${hadoop.version}..."
$HADOOP_SBIN/hadoop namenode -format
