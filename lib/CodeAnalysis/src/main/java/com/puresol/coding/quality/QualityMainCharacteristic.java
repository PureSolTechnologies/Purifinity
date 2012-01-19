package com.puresol.coding.quality;

import java.io.IOException;

import javax.i18n4java.Translator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.utils.JARUtilities;

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

    private static final Logger logger = LoggerFactory
	    .getLogger(QualityMainCharacteristic.class);
    private static final Translator translator = Translator
	    .getTranslator(QualityMainCharacteristic.class);

    public abstract String getName();

    public String getDescription() {
	String directory = "/"
		+ getClass().getPackage().getName().replaceAll("\\.", "/");
	String file = name().toLowerCase() + "."
		+ Translator.getDefault().toString() + ".txt";
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
