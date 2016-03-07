@echo off

set BASEDIR=%cd%
echo "Base directory: " %BASEDIR%

set WILDFLY_HOME=%BASEDIR%\..\lib\wildfly-${wildfly.version}
echo "WildFly home: " %WILDFLY_HOME%

echo "Starting WildFly ${wildfly.version}..."
%WILDFLY_HOME%\bin\standalone.bat
