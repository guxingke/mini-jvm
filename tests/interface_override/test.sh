#!/usr/bin/env bash
set -e
exe=$1
rm -f test.result
$exe InterfaceImpl11 >> test.result
$exe InterfaceImpl12 >> test.result
$exe InterfaceImpl13 >> test.result
$exe InterfaceImpl14 >> test.result
$exe InterfaceImpl15 >> test.result
diff test.result test.expect 2>&1 > /dev/null
