ARG JENKINS_VERSION=latest
FROM jenkins/jenkins:${JENKINS_VERSION}

LABEL "maintainer" "Enrique Gonzalez (enrique@saucelabs.com)"

ARG JENKINS_VERSION
RUN echo Using Jenkins version ${JENKINS_VERSION}

USER root
RUN apt-get update && apt-get install -y maven
USER jenkins

#Skip Setup Wizard

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

# Copy plugin list and install before launch
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

