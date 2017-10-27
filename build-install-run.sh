#!/usr/bin/env bash

set -e

./build-install.sh

cd sample-android-app
../gradlew :app:clean
../gradlew :app:dependenciesOverview
cd ..