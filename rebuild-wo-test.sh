#!/bin/bash
mvn -f api/pom.xml -o clean install -DskipTests && \
mvn -f framework/pom.xml -o clean install -DskipTests && \
mvn -f client/pom.xml -o clean install -DskipTests
