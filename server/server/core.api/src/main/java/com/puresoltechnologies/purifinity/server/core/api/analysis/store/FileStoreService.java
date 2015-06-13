package com.puresoltechnologies.purifinity.server.core.api.analysis.store;

import javax.ejb.Local;
import javax.enterprise.event.Observes;

import com.puresoltechnologies.purifinity.server.core.api.preferences.SystemPreferenceChange;
import com.puresoltechnologies.purifinity.server.core.api.preferences.SystemPreferenceChangeEvent;

@Local
public interface FileStoreService extends FileStore {
	public void onSystemPreferenceChange(
			@Observes @SystemPreferenceChange SystemPreferenceChangeEvent event);

}
