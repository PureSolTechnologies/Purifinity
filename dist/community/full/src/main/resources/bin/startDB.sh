BASEDIR=$(dirname $0)
CASSANDRA_DIR=$BASEDIR/../lib/apache-cassandra-${cassandra.version}
echo "Cassandra directory: " $CASSANDRA_DIR

echo "Starting Cassandra ${cassandra.version}..."
CASSANDRA_BINDIR=$CASSANDRA_DIR/bin
cd $CASSANDRA_BINDIR && sh cassandra
