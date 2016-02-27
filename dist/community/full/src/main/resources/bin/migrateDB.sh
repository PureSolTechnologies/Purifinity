#!/bin/sh
BASEDIR=$(dirname $PWD/$0)
echo "Base directory: " $BASEDIR

DDL_DIR=$BASEDIR/../ddl
echo "DDL directory: " $DDL_DIR

DDL_JAR=$DDL_DIR/global.ddl-${project.version}.jar
echo "Running DDL for Purifinity ${project.version}..."
java -jar $DDL_JAR --migrate
