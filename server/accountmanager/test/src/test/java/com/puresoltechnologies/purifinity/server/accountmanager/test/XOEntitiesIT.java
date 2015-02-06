package com.puresoltechnologies.purifinity.server.accountmanager.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.buschmais.xo.api.ResultIterable;
import com.buschmais.xo.api.ResultIterator;
import com.buschmais.xo.api.XOManager;
import com.buschmais.xo.api.XOManagerFactory;
import com.buschmais.xo.api.bootstrap.XO;
import com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo.RoleVertex;
import com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo.UserVertex;
import com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo.UsersXOManager;

public class XOEntitiesIT {

    private static XOManagerFactory xoManagerFactory;
    private XOManager xoManager;

    @BeforeClass
    public static void connecXO() {
	xoManagerFactory = XO
		.createXOManagerFactory(UsersXOManager.XO_UNIT_NAME);
    }

    @Before
    public void createXOManager() {
	xoManager = xoManagerFactory.createXOManager();
    }

    @Test
    public void test() {
	ResultIterable<UserVertex> results = xoManager.find(UserVertex.class,
		"ludwig@puresol-technologies.com");
	ResultIterator<UserVertex> iterator = results.iterator();
	assertTrue(iterator.hasNext());
	UserVertex user = iterator.next();
	assertEquals("ludwig@puresol-technologies.com", user.getEmail());
	assertEquals("Rick-Rainer Ludwig", user.getName());
	RoleVertex role = user.getRole();
	assertNotNull(role);
	assertEquals("administrator", role.getId());
	assertEquals("Administrator", role.getName());
	assertNotNull(role.getUsersWithRole());
	assertEquals(2, role.getUsersWithRole().size());
    }

    @After
    public void closeXOManager() {
	xoManager.close();
    }

    @AfterClass
    public static void closeXO() {
	xoManagerFactory.close();
    }

}
