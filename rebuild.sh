#!/bin/bash
mvn -f api/pom.xml -o clean install && \
mvn -f commons/pom.xml -o clean install && \
mvn -f framework/pom.xml -o clean install && \
mvn -f client/pom.xml -o clean install
