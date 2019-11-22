#!/usr/bin/env bash

native-image -cp target/mini-jvm.jar \
  -H:Name=mini-jvm \
  -H:IncludeResources='help.txt' \
  -H:+ReportUnsupportedElementsAtRuntime \
  --no-server \
  com.gxk.jvm.Main
