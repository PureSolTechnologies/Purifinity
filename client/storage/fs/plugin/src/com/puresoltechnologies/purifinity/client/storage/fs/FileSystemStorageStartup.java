package com.puresoltechnologies.purifinity.client.storage.fs;

import com.puresoltechnologies.purifinity.client.common.osgi.AbstractStartup;
import com.puresoltechnologies.purifinity.framework.store.fs.Activator;

public class FileSystemStorageStartup extends AbstractStartup {

	public FileSystemStorageStartup() {
		super(Activator.class);
	}

}
