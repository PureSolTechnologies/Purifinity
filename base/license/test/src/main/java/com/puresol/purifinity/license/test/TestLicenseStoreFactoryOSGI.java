package com.puresol.purifinity.license.test;

import com.puresol.purifinity.license.creator.store.LicenseStore;
import com.puresol.purifinity.license.creator.store.LicenseStoreFactory;

public class TestLicenseStoreFactoryOSGI extends LicenseStoreFactory {

    @Override
    public LicenseStore createLicenseStore() {
	return new TestFileSystemLicenseStore();
    }

}
