package com.puresoltechnologies.purifinity.license.creator;

import com.puresoltechnologies.purifinity.license.creator.store.FileSystemLicenseStore;
import com.puresoltechnologies.purifinity.license.creator.store.LicenseStore;
import com.puresoltechnologies.purifinity.license.creator.store.LicenseStoreFactory;

public class LicenseStoreFactoryOSGI extends LicenseStoreFactory {

    @Override
    public LicenseStore createLicenseStore() {
	return new FileSystemLicenseStore();
    }

}
