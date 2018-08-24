@echo off

set BASEDIR=%cd%
echo "Base directory: " %BASEDIR%

set DDL_DIR=%BASEDIR%\..\ddl
echo "DDL directory: " %DDL_DIR%

set DDL_JAR=%DDL_DIR%\global.ddl-${project.version}.jar
echo "Running DDL for Purifinity ${project.version}..."
java -jar %DDL_JAR% --migrate
