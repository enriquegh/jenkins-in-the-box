import hudson.plugins.git.GitSCM;
import hudson.plugins.git.BranchSpec;
import hudson.model.FreeStyleProject;
import hudson.tasks.Shell;

import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition;

jenkins = Jenkins.instance;

scm = new GitSCM("https://github.com/saucelabs-sample-test-frameworks/Java-JUnit-Selenium");
scm.branches = [new BranchSpec("*/master")];
workflowJob = new WorkflowJob(jenkins, "sample-java-pipeline");
workflowJob.definition = new CpsScmFlowDefinition(scm, "Jenkinsfile");
jenkins.add(workflowJob, workflowJob.name);
