# Jenkins in the Box

## Prerequisites

- Have Docker installed and running
I used Docker Desktop for Mac ([link](https://www.docker.com/products/docker-desktop)).

## How to build

1. If you haven't already, clone this repository with git:
```bash
git clone https://github.com/enriquegh/jenkins-in-the-box.git
```
2. Build the image. The following Build Arguments are possible:

#### JENKINS_VERSION
Version of Jenkins you want to use. Default is set to latest.

#### ONDEMAND_PLUGIN_VERSION
Version of the Sauce OnDemand plugin you want to be installed. Default is set to latest.
For available versions of the plugin see [here](https://updates.jenkins-ci.org/download/plugins/sauce-ondemand/).

#### DOCKER_SAUCE_USERNAME
Sauce Labs username used to create a credentials object. Default is set to foo.

#### DOCKER_SAUCE_ACCESS_KEY
Sauce Labs access key used to create a credentials object. Default is set to bar.

### Build command:
```bash
docker build --build-arg JENKINS_VERSION=latest --build-arg ONDEMAND_PLUGIN_VERSION=latest --build-arg DOCKER_SAUCE_USERNAME=$SAUCE_USERNAME --build-arg DOCKER_SAUCE_ACCESS_KEY==$SAUCE_ACCESS_KEY -t support_jenkins .
```

NOTE: the support_jenkins is the name of the tag of the image. This can be set to anything you want as long as it's used the same exact way when starting the container.

## How to Run

```bash
docker run -p 8080:8080 -p 50000:50000  support_jenkins
```
Port 8080 and 50000 need to be exposed in order to use the UI properly.