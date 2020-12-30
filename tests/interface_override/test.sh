#!/usr/bin/env bash
set -e
exe=$1
rm -f test.result
$exe -cp . InterfaceImpl11 >> test.result
$exe -cp . InterfaceImpl12 >> test.result
$exe -cp . InterfaceImpl13 >> test.result
$exe -cp . InterfaceImpl14 >> test.result
$exe -cp . InterfaceImpl15 >> test.result
diff test.result test.expect 2>&1 > /dev/null
