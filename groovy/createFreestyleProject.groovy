import hudson.model.FreeStyleProject;
import hudson.plugins.git.GitSCM;
import hudson.plugins.git.BranchSpec;
import hudson.plugins.sauce_ondemand.SauceOnDemandBuildWrapper;
import hudson.plugins.sauce_ondemand.SauceOnDemandTestPublisher
import hudson.plugins.sauce_ondemand.SeleniumInformation;
import hudson.tasks.Maven;
import jenkins.model.Jenkins


jenkins = Jenkins.instance;

// Create free project in the main Jenkins instance.
FreeStyleProject freeProject = new FreeStyleProject(jenkins,"sample-java-freestyle");

// Create Git SCM option and set to master branch
scm2 = new GitSCM("https://github.com/saucelabs-sample-test-frameworks/Java-JUnit-Selenium");
scm2.branches = [new BranchSpec("*/master")];

// Create Maven Builder
Maven maven = new Maven("clean test",null);

// Create "Sauce Labs Support" instance
// This is only setting credentials to the ID created on createSauceCredentials.groovy
SauceOnDemandBuildWrapper sauceWrapper = new SauceOnDemandBuildWrapper(false,null, "saucelabs",new SeleniumInformation(null, null),null,null,"",null,false,false,false,false,false,null,null,null,false);

SauceOnDemandTestPublisher testPublisher = new SauceOnDemandTestPublisher();

// add Maven Builder
freeProject.buildersList.add(maven);

// add Git repo
freeProject.scm = scm2

// Add Sauce Support usage
freeProject.getBuildWrappersList().add(sauceWrapper);

// Add Sauce test publisher
freeProject.getPublishersList().add(testPublisher);

// Add project and settings to instance
jenkins.add(freeProject, freeProject.name);
