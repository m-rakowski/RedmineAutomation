#!/usr/bin/env bash


cd ~/lab/apps/ruby-redmine-automated-tests
./test_setup.sh

cd ~/lab/apps/RedmineAutomation
mvn clean install -D chosen.execution.browser=$1

cd ~/lab/apps/RedmineAutomation/target;
allure generate;


cp -r allure-report ~/lab/results_run_locally/Java_$1_$(date +"%b%d_%I-%M%p")

cd ~/lab/apps/RedmineAutomation