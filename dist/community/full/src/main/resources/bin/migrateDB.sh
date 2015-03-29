BASEDIR=$(dirname $0)
DDL_DIR=$BASEDIR/../ddl
echo "DDL directory: " $DDL_DIR

DDL_JAR=$DDL_DIR/global.ddl-${purifinity.version}.jar
echo "Running DDL for Purifinity ${purifinity.version}..."
java -jar $DDL_JAR --migrate
