package com.puresol.gui.osgi;

import javax.i18n4java.Translator;

import org.osgi.framework.Bundle;

public class OSGiUtils {

	private static final Translator translator = Translator
			.getTranslator(OSGiUtils.class);

	public static String getTextForState(int state) {
		switch (state) {
		case Bundle.UNINSTALLED:
			return translator.i18n("uninstalled");
		case Bundle.INSTALLED:
			return translator.i18n("installed");
		case Bundle.RESOLVED:
			return translator.i18n("resolved");
		case Bundle.STARTING:
			return translator.i18n("starting");
		case Bundle.STOPPING:
			return translator.i18n("stopping");
		case Bundle.ACTIVE:
			return translator.i18n("active");
		}
		return translator.i18n("unknown");
	}
}
