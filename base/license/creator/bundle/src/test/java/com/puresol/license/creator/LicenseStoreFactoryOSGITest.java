package com.puresol.license.creator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.puresol.license.creator.store.LicenseStoreFactory;

public class LicenseStoreFactoryOSGITest {

    @Test
    public void test() {
	LicenseStoreFactory factory = LicenseStoreFactory.getFactory();
	assertNotNull(factory);
	assertEquals(LicenseStoreFactoryOSGI.class, factory.getClass());
    }

}
