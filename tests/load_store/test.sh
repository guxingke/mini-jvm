#!/usr/bin/env bash
set -e
exe=$1
$exe -cp . ILoadStore > itest.result
diff itest.result itest.expect 2>&1 > /dev/null

$exe -cp . LLoadStore > ltest.result
diff ltest.result ltest.expect 2>&1 > /dev/null

#$exe FLoadStore > ftest.result
#diff ftest.result ftest.expect 2>&1 > /dev/null

#$exe DLoadStore > dtest.result
#diff dtest.result dtest.expect 2>&1 > /dev/null
