#!/usr/bin/env bash
set -e
exe=$1
rm -f test.result
$exe InterfaceImpl1 >> test.result
$exe InterfaceImpl2 >> test.result
diff test.result test.expect 2>&1 > /dev/null
