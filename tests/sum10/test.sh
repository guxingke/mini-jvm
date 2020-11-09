#!/usr/bin/env bash
set -e

exe=$1

$exe -cp . Sum10M > test.result

diff test.result test.expect 2>&1 > /dev/null
