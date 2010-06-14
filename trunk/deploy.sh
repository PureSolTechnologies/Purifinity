#!/bin/sh

cp -r bin/* /opt/qsys/lib/
find /opt/qsys/lib/* -type d -exec chmod 777 {} \;
find /opt/qsys/lib/* -type f -exec chmod 666 {} \;
