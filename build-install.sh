#!/usr/bin/env bash

set -e

rm -rf repo
./gradlew clean
./gradlew :dependencies-overview:build
# useLocalVersion is needed to deploy locally library version that will be further referenced
# in integration project. This is to avoid accidental usage of remote library version
# published to maven central and to always consume latest version and make sure all tests are passing
./gradlew publishToMavenLocal -DuseLocalVersion=true -Dmaven.repo.local="$(pwd)"/repo
