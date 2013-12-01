package com.puresoltechnologies.purifinity.license.creator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.puresoltechnologies.purifinity.license.creator.LicenseStoreFactoryOSGI;
import com.puresoltechnologies.purifinity.license.creator.store.LicenseStoreFactory;

public class LicenseStoreFactoryOSGITest {

    @Test
    public void test() {
	LicenseStoreFactory factory = LicenseStoreFactory.getFactory();
	assertNotNull(factory);
	assertEquals(LicenseStoreFactoryOSGI.class, factory.getClass());
    }

}
