ARG JENKINS_VERSION=latest
FROM jenkins/jenkins:${JENKINS_VERSION}

LABEL "maintainer" "Enrique Gonzalez (enrique@saucelabs.com)"

ARG JENKINS_VERSION
RUN echo Using Jenkins version ${JENKINS_VERSION}

USER root
RUN apt-get update && apt-get install -y maven
USER jenkins

#Skip Setup Wizard

# runSetupWizard avoids Jenkins setup
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

# Avoid OpenJDK Surefire bug. See https://issues.apache.org/jira/browse/SUREFIRE-1588
# Can be removed once Jenkins/Debian image adds OpenJDK version 1.8.0_191
ENV _JAVA_OPTIONS="-Djdk.net.URLClassPath.disableClassPathURLCheck=true"

# Copy plugin list and install before launch
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# Download Jenkins OnDemand plugin. Default to latest
ARG ONDEMAND_PLUGIN_VERSION=latest
RUN /usr/local/bin/install-plugins.sh "sauce-ondemand:${ONDEMAND_PLUGIN_VERSION}"

# Add Sauce Username and Access Key
ARG DOCKER_SAUCE_USERNAME=foo
ARG DOCKER_SAUCE_ACCESS_KEY=bar

ENV DOCKER_SAUCE_USERNAME=$DOCKER_SAUCE_USERNAME
ENV DOCKER_SAUCE_ACCESS_KEY=$DOCKER_SAUCE_ACCESS_KEY

# Move groovy files to Docker
COPY groovy/* /usr/share/jenkins/ref/init.groovy.d/