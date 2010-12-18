package com.puresol.gui.osgi;

import javax.i18n4java.Translator;

import org.osgi.framework.Bundle;

public class OSGiUtils {

	private static final Translator translator = Translator
			.getTranslator(OSGiUtils.class);

	public static String getTextForState(int state) {
		if (state == Bundle.UNINSTALLED) {
			return translator.i18n("uninstalled");
		} else if (state == Bundle.INSTALLED) {
			return translator.i18n("installed");
		} else if (state == Bundle.RESOLVED) {
			return translator.i18n("resolved");
		} else if (state == Bundle.STARTING) {
			return translator.i18n("starting");
		} else if (state == Bundle.STOPPING) {
			return translator.i18n("stopping");
		} else if (state == Bundle.ACTIVE) {
			return translator.i18n("active");
		}
		return translator.i18n("unknown");
	}
}
