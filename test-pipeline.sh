#!/bin/bash
set -euo pipefail
cd ${0%/*}

echo Cleaning results directory
rm -rf allure-results/ target/

echo Executing unit tests
mvn test || true

echo Executing integration tests
mvn test-compile failsafe:integration-test failsafe:verify || true


echo Uploading test results
allurectl upload --project-id=2 --launch-name "Launch at $(date -u +'%F %R')" allure-results


# alternative

#export ALLURE_PROJECT_ID=2
#export ALLURE_LAUNCH_NAME="Launch at $(date -u +'%F %R')" 
#export ALLURE_RESULTS="./allure-results"

#allurectl watch -- mvn clean test
#allurectl watch -- mvn clean test-compile failsafe:integration-test failsafe:verify
