#!/usr/bin/env bash
VERSION="1.0-SNAPSHOT"

case $1 in
"start")
  echo "Starting cekit-cacher bot."
  java -jar /mnt/cekit-cacher-storage/data/cekit-cache-server-${VERSION}-runner.jar &
  echo $! > /mnt/cekit-cacher-storage/data/cacher.pid
  ;;
"restart")
  echo "Restarting cekit-cacher bot."
  kill -15 `cat /mnt/cekit-cacher-storage/data/cacher.pid`
  rm -rf  /mnt/cekit-cacher-storage/data/cacher.pid
  java -jar /mnt/cekit-cacher-storage/data/cekit-cache-server-${VERSION}-runner.jar &
  echo $! > /mnt/cekit-cacher-storage/data/cacher.pid
  ;;
"stop")
  echo "Stopping cekit-cacher bot."
  kill -15 `cat /mnt/cekit-cacher-storage/data/cacher.pid`
  rm -rf  /mnt/cekit-cacher-storage/data/cacher.pid
  ;;
*)
  echo "Invalid parameters, use, stop, start or restart"
  ;;
esac
