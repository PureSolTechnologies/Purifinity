package com.puresol.coding.analysis.api.quality;

import java.io.IOException;

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
			return "Suitability";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.FUNCTIONALITY;
		}
	},
	ACCURACY {
		@Override
		public String getName() {
			return "Accuracy";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.FUNCTIONALITY;
		}
	},
	INTEROPERABILITY {
		@Override
		public String getName() {
			return "Interoperability";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.FUNCTIONALITY;
		}
	},
	SECURIY {
		@Override
		public String getName() {
			return "Security";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.FUNCTIONALITY;
		}
	},
	MATURITY {
		@Override
		public String getName() {
			return "Maturiy";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.RELIABILITY;
		}
	},
	FAULT_TOLERANCE {
		@Override
		public String getName() {
			return "Fault Tolerance";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.RELIABILITY;
		}
	},
	RECOVERABILITY {
		@Override
		public String getName() {
			return "Recoverability";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.RELIABILITY;
		}
	},
	UNDERSTANDABILITY {
		@Override
		public String getName() {
			return "Understandability";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.USABILITY;
		}
	},
	LEARNABILITY {
		@Override
		public String getName() {
			return "Learnability";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.USABILITY;
		}
	},
	OPERABILITY {
		@Override
		public String getName() {
			return "Operability";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.USABILITY;
		}
	},
	ATTRACTIVENESS {
		@Override
		public String getName() {
			return "Attractiveness";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.USABILITY;
		}
	},
	TIME_BEHAVIOR {
		@Override
		public String getName() {
			return "Time Behavior";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.EFFICIENCY;
		}
	},
	RESOURCE_UTILIZATION {
		@Override
		public String getName() {
			return "Resource Utilization";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.EFFICIENCY;
		}
	},
	ANALYSABILITY {
		@Override
		public String getName() {
			return "Analysability";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.MAINTAINABILITY;
		}
	},
	CHANGEABILITY {
		@Override
		public String getName() {
			return "Changeability";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.MAINTAINABILITY;
		}
	},
	STABILITY {
		@Override
		public String getName() {
			return "Stability";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.MAINTAINABILITY;
		}
	},
	TESTABILITY {
		@Override
		public String getName() {
			return "Testability";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.MAINTAINABILITY;
		}
	},
	ADAPTABILITY {
		@Override
		public String getName() {
			return "Adaptability";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.PORTABILITY;
		}
	},
	INSTALLABILITY {
		@Override
		public String getName() {
			return "Installability";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.PORTABILITY;
		}
	},
	CO_EXISTANCE {
		@Override
		public String getName() {
			return "Co-existence";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.PORTABILITY;
		}
	},
	REPLACEABILITY {
		@Override
		public String getName() {
			return "Replaceability";
		}

		@Override
		public QualityMainCharacteristic getMainCharacteristic() {
			return QualityMainCharacteristic.PORTABILITY;
		}
	};

	private static final Logger logger = LoggerFactory
			.getLogger(QualityCharacteristic.class);

	public abstract String getName();

	public abstract QualityMainCharacteristic getMainCharacteristic();

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
