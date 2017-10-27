#!/usr/bin/env bash

set -e

rm -rf repo
./gradlew clean
./gradlew :dependencies-overview:build
./gradlew :dependencies-overview:uploadArchives -PlocalDeploy