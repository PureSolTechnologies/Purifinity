package com.puresol.license.creator;

import com.puresol.license.creator.store.FileSystemLicenseStore;
import com.puresol.license.creator.store.LicenseStore;
import com.puresol.license.creator.store.LicenseStoreFactory;

public class LicenseStoreFactoryOSGI extends LicenseStoreFactory {

    @Override
    public LicenseStore createLicenseStore() {
	return new FileSystemLicenseStore();
    }

}
