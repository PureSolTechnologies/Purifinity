package com.puresol.coding.quality;

import java.io.IOException;

import javax.i18n4java.Translator;

import org.apache.log4j.Logger;

import com.puresol.utils.ResourceUtilities;

public enum QualityMainCharacteristic {

	FUNCTIONALITY {
		@Override
		public String getName() {
			return translator.i18n("Functionality");
		}
	},
	RELIABILITY {
		@Override
		public String getName() {
			return translator.i18n("Reliability");
		}
	},
	USABILITY {
		@Override
		public String getName() {
			return translator.i18n("Usability");
		}
	},
	EFFICIENCY {
		@Override
		public String getName() {
			return translator.i18n("Efficiency");
		}
	},
	MAINTAINABILITY {
		@Override
		public String getName() {
			return translator.i18n("Maintainability");
		}
	},
	PORTABILITY {
		@Override
		public String getName() {
			return translator.i18n("Portability");
		}
	};

	private static final Logger logger = Logger
			.getLogger(QualityMainCharacteristic.class);
	private static final Translator translator = Translator
			.getTranslator(QualityMainCharacteristic.class);

	public abstract String getName();

	public String getDescription() {
		String directory = "/"
				+ getClass().getPackage().getName().replaceAll("\\.", "/");
		String file = name().toLowerCase() + "."
				+ Translator.getDefaultLanguage() + ".txt";
		try {
			return ResourceUtilities.readResourceFileToString(directory + "/"
					+ file);
		} catch (IOException e) {
			logger.warn("No localized version of '" + file.toString()
					+ "' is available!");
		}
		file = name().toLowerCase() + ".en.txt";
		try {
			return ResourceUtilities.readResourceFileToString(directory + "/"
					+ file);
		} catch (IOException e) {
			logger.warn("No file '" + file.toString() + "' is available!");
		}
		return "";
	}
}
