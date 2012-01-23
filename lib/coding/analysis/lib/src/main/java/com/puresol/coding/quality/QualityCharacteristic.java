package com.puresol.coding.quality;

import java.io.IOException;

import javax.i18n4java.Translator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.utils.JARUtilities;

/**
 * This enumeration contains a list of all quality characteristics which are
 * evaluated within a special evaluator.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum QualityCharacteristic {

    SUITABILITY {
	@Override
	public String getName() {
	    return translator.i18n("Suitability");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.FUNCTIONALITY;
	}
    },
    ACCURACY {
	@Override
	public String getName() {
	    return translator.i18n("Accuracy");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.FUNCTIONALITY;
	}
    },
    INTEROPERABILITY {
	@Override
	public String getName() {
	    return translator.i18n("Interoperability");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.FUNCTIONALITY;
	}
    },
    SECURIY {
	@Override
	public String getName() {
	    return translator.i18n("Security");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.FUNCTIONALITY;
	}
    },
    MATURITY {
	@Override
	public String getName() {
	    return translator.i18n("Maturiy");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.RELIABILITY;
	}
    },
    FAULT_TOLERANCE {
	@Override
	public String getName() {
	    return translator.i18n("Fault Tolerance");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.RELIABILITY;
	}
    },
    RECOVERABILITY {
	@Override
	public String getName() {
	    return translator.i18n("Recoverability");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.RELIABILITY;
	}
    },
    UNDERSTANDABILITY {
	@Override
	public String getName() {
	    return translator.i18n("Understandability");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.USABILITY;
	}
    },
    LEARNABILITY {
	@Override
	public String getName() {
	    return translator.i18n("Learnability");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.USABILITY;
	}
    },
    OPERABILITY {
	@Override
	public String getName() {
	    return translator.i18n("Operability");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.USABILITY;
	}
    },
    ATTRACTIVENESS {
	@Override
	public String getName() {
	    return translator.i18n("Attractiveness");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.USABILITY;
	}
    },
    TIME_BEHAVIOR {
	@Override
	public String getName() {
	    return translator.i18n("Time Behavior");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.EFFICIENCY;
	}
    },
    RESOURCE_UTILIZATION {
	@Override
	public String getName() {
	    return translator.i18n("Resource Utilization");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.EFFICIENCY;
	}
    },
    ANALYSABILITY {
	@Override
	public String getName() {
	    return translator.i18n("Analysability");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.MAINTAINABILITY;
	}
    },
    CHANGEABILITY {
	@Override
	public String getName() {
	    return translator.i18n("Changeability");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.MAINTAINABILITY;
	}
    },
    STABILITY {
	@Override
	public String getName() {
	    return translator.i18n("Stability");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.MAINTAINABILITY;
	}
    },
    TESTABILITY {
	@Override
	public String getName() {
	    return translator.i18n("Testability");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.MAINTAINABILITY;
	}
    },
    ADAPTABILITY {
	@Override
	public String getName() {
	    return translator.i18n("Adaptability");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.PORTABILITY;
	}
    },
    INSTALLABILITY {
	@Override
	public String getName() {
	    return translator.i18n("Installability");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.PORTABILITY;
	}
    },
    CO_EXISTANCE {
	@Override
	public String getName() {
	    return translator.i18n("Co-existence");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.PORTABILITY;
	}
    },
    REPLACEABILITY {
	@Override
	public String getName() {
	    return translator.i18n("Replaceability");
	}

	@Override
	public QualityMainCharacteristic getMainCharacteristic() {
	    return QualityMainCharacteristic.PORTABILITY;
	}
    };

    private static final Logger logger = LoggerFactory
	    .getLogger(QualityCharacteristic.class);
    private static final Translator translator = Translator
	    .getTranslator(QualityCharacteristic.class);

    public abstract String getName();

    public abstract QualityMainCharacteristic getMainCharacteristic();

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
