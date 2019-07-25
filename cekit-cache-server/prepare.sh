#!/bin/bash

MD5SUM_JAR=$(ls target/*-runner.jar | xargs md5sum)

cekit-cache add --md5=${MD5SUM_JAR}

export MD5SUM=$(echo $MD5SUM_JAR | awk '{print $1}')
echo "md5sum is $MD5SUM"
cat ./cekit-modules/configure-cekit-cacher/base-module.yaml | envsubst > ./cekit-modules/configure-cekit-cacher/module.yaml