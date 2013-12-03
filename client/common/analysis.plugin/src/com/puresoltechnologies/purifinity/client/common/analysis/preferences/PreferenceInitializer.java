package com.puresoltechnologies.purifinity.client.common.analysis.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.puresoltechnologies.purifinity.client.common.analysis.Activator;

/**
 * This class is the central point for preference default value settings.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public PreferenceInitializer() {
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore preferenceStore = Activator.getDefault()
				.getPreferenceStore();
		NewAnalysisPreferencePage
				.setDefaultValuesToPreferencesStore(preferenceStore);
	}

}
