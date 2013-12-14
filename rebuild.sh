#!/bin/bash
cd api && mvn -o clean install -DskipTests && \
cd ../commons && mvn -o clean install -DskipTests && \
cd ../framework && mvn -o clean install -DskipTests && \
cd ../client && mvn -o clean install -DskipTests
