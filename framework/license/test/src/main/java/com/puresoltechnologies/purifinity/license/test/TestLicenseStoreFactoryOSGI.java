package com.puresoltechnologies.purifinity.license.test;

import com.puresoltechnologies.purifinity.license.creator.store.LicenseStore;
import com.puresoltechnologies.purifinity.license.creator.store.LicenseStoreFactory;

public class TestLicenseStoreFactoryOSGI extends LicenseStoreFactory {

    @Override
    public LicenseStore createLicenseStore() {
	return new TestFileSystemLicenseStore();
    }

}
