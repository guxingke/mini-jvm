#!/usr/bin/env bash
_filepath=$(cd "$(dirname "$0")"; pwd)

function test_one() {
  exe=$1
  test=$2
  echo "testing $test"

  pushd tests/$test > /dev/null
    R="./test.sh $exe"
    # run
    $R > test.result
    [[ 0 -ne $? ]] && echo "failed on $test" && exit 1;
  popd > /dev/null
}

export JAVA=${_filepath}/bin/java
for x in $(ls -d tests/issues_28);
do 
  file=$(basename $x)
  test_one $JAVA $file
done

echo ""
echo "pass"

