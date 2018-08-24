#!/bin/sh
BASEDIR=$(dirname $PWD/$0)
echo "Base directory: " $BASEDIR

WILDFLY_HOME=$BASEDIR/../lib/wildfly-${wildfly.version}
echo "WildFly home: " $CASSANDRA_DIR

WILDFLY_START_SCRIPT=$WILDFLY_HOME/bin/standalone.sh
echo "Starting WildFly ${wildfly.version}..."
sh $WILDFLY_START_SCRIPT
