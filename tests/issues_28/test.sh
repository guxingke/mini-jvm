#!/usr/bin/env bash
set -e 

exe=$1

rm HelloWorld.class HelloWorldSuper.class
javac HelloWorldSuper.java HelloWorld.java

$exe -cp . HelloWorld > test.result

diff test.result test.expect 2>&1 > /dev/null
