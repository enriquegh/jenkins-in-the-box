ARG JENKINS_VERSION=latest
FROM jenkins/jenkins:${JENKINS_VERSION}

ARG JENKINS_VERSION
RUN echo Using Jenkins version ${JENKINS_VERSION}

USER root
RUN apt-get update && apt-get install -y maven
USER jenkins

