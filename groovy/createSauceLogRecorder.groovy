import hudson.logging.*;
import jenkins.model.*;

import java.util.logging.Level;


LogRecorderManager logManager = Jenkins.instance.getLog();

LogRecorder sauceLogRecorder = new LogRecorder("Sauce OnDemand plugin Logs");
logManager.logRecorders.put("Sauce OnDemand plugin Logs", sauceLogRecorder);

LogRecorder.Target t1 = new LogRecorder.Target("com.saucelabs.ci", Level.ALL);
LogRecorder.Target t2 = new LogRecorder.Target("com.saucelabs.jenkins", Level.ALL);
LogRecorder.Target t3 = new LogRecorder.Target("hudson.plugins.sauce_ondemand", Level.ALL);



sauceLogRecorder.targets.add(t1);
sauceLogRecorder.targets.add(t2);
sauceLogRecorder.targets.add(t3);

sauceLogRecorder.save();

t1.enable();
t2.enable();
t3.enable();