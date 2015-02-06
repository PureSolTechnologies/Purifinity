package com.puresoltechnologies.purifinity.server.accountmanager.test;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.AccountManagerRestInterface;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.Role;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.User;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;

public class AccountManagerRestServiceIT extends
	AbstractAccountManagerClientTest {

    private static AccountManagerRestInterface proxy = null;

    private static ResteasyClient client;

    @BeforeClass
    public static void createRestClient() {
	client = new ResteasyClientBuilder().build();
	ResteasyWebTarget webTarget = client
		.target("http://localhost:8080/accountmanager/rest");
	proxy = webTarget.proxy(AccountManagerRestInterface.class);
    }

    @AfterClass
    public static void closeRestClient() {
	client.close();
    }

    @Before
    public void checkPreConditions() {
	Set<Role> roles = proxy.getRoles();
	assertEquals(3, roles.size());
	Set<User> users = proxy.getUsers();
	assertEquals(4, users.size());
    }

    @Test
    public void test() throws PasswordCreationException {
	String account = proxy.createAccount("abcde@abc.de", "Abc123!§$%");
	Set<User> users = proxy.getUsers();
	assertEquals(5, users.size());
	proxy.removeAccount(account);
    }
}
