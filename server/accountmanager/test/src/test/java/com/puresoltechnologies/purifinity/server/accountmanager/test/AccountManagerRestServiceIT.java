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

import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManagerException;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.AccountManagerRestInterface;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.CreateAccountEntity;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.Role;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.User;

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
    public void test() throws AccountManagerException {
	String email = "abcde@abc.de";
	proxy.createAccount(new CreateAccountEntity(email, "Abc123!§$%",
		"engineer"));
	Set<User> users = proxy.getUsers();
	assertEquals(5, users.size());
	proxy.removeAccount(email);
    }
}
