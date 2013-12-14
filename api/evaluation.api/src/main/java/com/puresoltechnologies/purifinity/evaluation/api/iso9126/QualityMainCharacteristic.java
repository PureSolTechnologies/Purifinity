package com.puresoltechnologies.purifinity.evaluation.api.iso9126;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.misc.JARUtilities;

public enum QualityMainCharacteristic {

	FUNCTIONALITY {
		@Override
		public String getName() {
			return "Functionality";
		}
	},
	RELIABILITY {
		@Override
		public String getName() {
			return "Reliability";
		}
	},
	USABILITY {
		@Override
		public String getName() {
			return "Usability";
		}
	},
	EFFICIENCY {
		@Override
		public String getName() {
			return "Efficiency";
		}
	},
	MAINTAINABILITY {
		@Override
		public String getName() {
			return "Maintainability";
		}
	},
	PORTABILITY {
		@Override
		public String getName() {
			return "Portability";
		}
	};

	private static final Logger logger = LoggerFactory
			.getLogger(QualityMainCharacteristic.class);

	public abstract String getName();

	public String getDescription() {
		String directory = "/"
				+ getClass().getPackage().getName().replaceAll("\\.", "/");
		String file = name().toLowerCase() + ".txt";
		try {
			return JARUtilities.readResourceFileToString(getClass()
					.getResource(directory + "/" + file));
		} catch (IOException e) {
			logger.warn("No localized version of '" + file.toString()
					+ "' is available!");
		}
		file = name().toLowerCase() + ".en.txt";
		try {
			return JARUtilities.readResourceFileToString(getClass()
					.getResource(directory + "/" + file));
		} catch (IOException e) {
			logger.warn("No file '" + file.toString() + "' is available!");
		}
		return "";
	}
}
