#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Apache Maven Wrapper startup script for Unix
# ----------------------------------------------------------------------------

# Determine the project base directory
if [ -z "$MAVEN_PROJECTBASEDIR" ]; then
  MAVEN_PROJECTBASEDIR=$( cd "$( dirname "$0" )" && pwd )
  export MAVEN_PROJECTBASEDIR
fi

MAVEN_OPTS="${MAVEN_OPTS:--Xmx64m -Xms64m}"
export MAVEN_OPTS

# Find java
if [ -z "$JAVA_HOME" ]; then
  JAVACMD="java"
else
  JAVACMD="$JAVA_HOME/bin/java"
fi

# Check if java is available
if ! command -v "$JAVACMD" >/dev/null 2>&1; then
  echo "Error: JAVA_HOME is not set and no 'java' command could be found in your PATH." >&2
  exit 1
fi

WRAPPER_JAR="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar"
WRAPPER_PROPERTIES="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.properties"

if [ -f "$WRAPPER_JAR" ]; then
  exec "$JAVACMD" $MAVEN_OPTS -jar "$WRAPPER_JAR" "$@"
else
  echo "Downloading Maven Wrapper..."
  mkdir -p "$MAVEN_PROJECTBASEDIR/.mvn/wrapper"
  curl -o "$WRAPPER_JAR" "https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.3.2/maven-wrapper-3.3.2.jar"
  if [ $? -ne 0 ]; then
    echo "Failed to download Maven Wrapper jar" >&2
    exit 1
  fi
  exec "$JAVACMD" $MAVEN_OPTS -jar "$WRAPPER_JAR" "$@"
fi
