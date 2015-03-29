BASEDIR=$(dirname $0)
echo "Base directory: " $BASEDIR
WILDFLY_DIR=$BASEDIR/../lib/wildfly-${wildfly.version}
echo "WildFly directory: " $CASSANDRA_DIR
WILDFLY_START_SCRIPT=$WILDFLY_DIR/bin/standalone.sh
echo "Starting WildFly ${wildfly.version}..."
sh $WILDFLY_START_SCRIPT
