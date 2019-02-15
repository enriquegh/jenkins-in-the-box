import hudson.plugins.sauce_ondemand.credentials.*
import com.cloudbees.plugins.credentials.CredentialsScope;
import com.cloudbees.plugins.credentials.Credentials;
import com.cloudbees.plugins.credentials.SystemCredentialsProvider;
import com.cloudbees.plugins.credentials.domains.Domain;

String createdCredentialId = UUID.randomUUID().toString();
SauceCredentials sauceCredentials = new SauceCredentials(CredentialsScope.GLOBAL, createdCredentialId,"foo", "bar", "Created by Dockerfile");

final SystemCredentialsProvider credentialsProvider = SystemCredentialsProvider.getInstance();
final Map<Domain, List<Credentials>> credentialsMap = credentialsProvider.getDomainCredentialsMap();

final Domain domain = Domain.global();

if (credentialsMap.get(domain) == null) {
	credentialsMap.put(domain, Collections.EMPTY_LIST);
}
credentialsMap.get(domain).add(sauceCredentials);

credentialsProvider.setDomainCredentialsMap(credentialsMap);
credentialsProvider.save();