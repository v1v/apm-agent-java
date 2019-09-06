#!/usr/bin/env bash
source /usr/local/bin/bash_standard_lib.sh

JAVA_HOME=$HOME/.java/java10 MAVEN_OPTS=-Dorg.slf4j.simpleLogger.defaultLogLevel=warn ./mvnw --batch-mode clean verify || true

(retry 2 docker pull docker.elastic.co/observability-ci/weblogic:12.2.1.3-dev) \
  && docker tag docker.elastic.co/observability-ci/weblogic:12.2.1.3-dev store/oracle/weblogic:12.2.1.3-dev
