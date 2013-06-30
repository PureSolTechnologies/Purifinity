package com.puresol.purifinity.license.creator;

import com.puresol.purifinity.license.creator.store.FileSystemLicenseStore;
import com.puresol.purifinity.license.creator.store.LicenseStore;
import com.puresol.purifinity.license.creator.store.LicenseStoreFactory;

public class LicenseStoreFactoryOSGI extends LicenseStoreFactory {

    @Override
    public LicenseStore createLicenseStore() {
	return new FileSystemLicenseStore();
    }

}
