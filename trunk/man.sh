#!/bin/sh

cd designs && ./generate.pl
cd ..
cd man && ./generate.pl
