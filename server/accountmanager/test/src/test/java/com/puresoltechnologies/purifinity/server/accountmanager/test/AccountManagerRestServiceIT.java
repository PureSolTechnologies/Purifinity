package com.puresoltechnologies.purifinity.server.accountmanager.test;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresoltechnologies.purifinity.server.accountmanager.domain.Role;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.User;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.AccountManagerRestInterface;

public class AccountManagerRestServiceIT extends
	AbstractAccountManagerClientTest {

    private static AccountManagerRestInterface proxy = null;

    @BeforeClass
    public static void createRestClient() {
	ResteasyClient client = new ResteasyClientBuilder().build();
	ResteasyWebTarget webTarget = client
		.target("http://localhost:8080/accountmanager/rest");
	proxy = webTarget.proxy(AccountManagerRestInterface.class);
    }

    @Before
    public void checkPreConditions() {
	Set<Role> roles = proxy.getRoles();
	assertEquals(0, roles.size());
	Set<User> users = proxy.getUsers();
	assertEquals(0, users.size());
    }

    @Test
    public void test() {

    }
}
