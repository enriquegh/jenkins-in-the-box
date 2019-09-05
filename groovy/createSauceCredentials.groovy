import hudson.plugins.sauce_ondemand.credentials.*
import com.cloudbees.plugins.credentials.CredentialsScope;
import com.cloudbees.plugins.credentials.Credentials;
import com.cloudbees.plugins.credentials.SystemCredentialsProvider;
import com.cloudbees.plugins.credentials.domains.Domain;

String SAUCE_USERNAME = System.getenv("DOCKER_SAUCE_USERNAME");
String SAUCE_ACCESS_KEY = System.getenv("DOCKER_SAUCE_ACCESS_KEY");

String restEndpointUS = "https://saucelabs.com/";
String restEndpointEU = "https://eu-central-1.saucelabs.com/";
String restEndpointHeadless = "https://us-east-1.saucelabs.com/";

HashMap<String, String> restEndpoints = new HashMap<>();
restEndpoints.put("saucelabs", restEndpointUS);
restEndpoints.put("saucelabs-eu", restEndpointEU);
restEndpoints.put("saucelabs-headless", restEndpointHeadless);

final SystemCredentialsProvider credentialsProvider = SystemCredentialsProvider.getInstance();
final Map<Domain, List<Credentials>> credentialsMap = credentialsProvider.getDomainCredentialsMap();

final Domain domain = Domain.global();

if (credentialsMap.get(domain) == null) {
	credentialsMap.put(domain, Collections.EMPTY_LIST);
}

for (Map.Entry<String, String> entry : restEndpoints.entrySet()) {

	SauceCredentials sauceCredentials = new SauceCredentials(CredentialsScope.GLOBAL, entry.key,SAUCE_USERNAME, SAUCE_ACCESS_KEY, entry.value, "Created by Dockerfile. " + entry.key);
	credentialsMap.get(domain).add(sauceCredentials);

}


credentialsProvider.setDomainCredentialsMap(credentialsMap);
credentialsProvider.save();