package com.puresol.coding.client.application.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.puresol.coding.client.application.Activator;

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
