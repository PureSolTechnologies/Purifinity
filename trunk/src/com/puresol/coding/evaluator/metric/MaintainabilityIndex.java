/***************************************************************************
 *
 *   MaintainabilityIndex.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.evaluator.metric;

import java.util.ArrayList;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.utils.Property;

public class MaintainabilityIndex implements Metric {

	private static final long serialVersionUID = 2789695185933616684L;

	private static final Translator translator = Translator
			.getTranslator(MaintainabilityIndex.class);

	public static final String NAME = translator.i18n("Maintainability Index");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	static {
		SUPPORTED_PROPERTIES.add(new Property(MaintainabilityIndex.class,
				"enabled",
				"Switches calculation of Maintainability Index on and off.",
				Boolean.class, "true"));
	}

	/**
	 * MaintainabilityIndex without comment.
	 */
	private double MIwoc;
	/**
	 * MaintainabilityIndex comment weight
	 */
	private double MIcw;
	/**
	 * MaintainabilityIndex
	 */
	private double MI;

	private final CodeRange codeRange;
	private final SLOCMetric slocMetric;
	private final McCabeMetric mcCabeMetric;
	private final HalsteadMetric halsteadMetric;

	public MaintainabilityIndex(CodeRange codeRange) {
		this.codeRange = codeRange;
		this.slocMetric = new SLOCMetric(codeRange);
		this.mcCabeMetric = new McCabeMetric(codeRange);
		this.halsteadMetric = new HalsteadMetric(codeRange);
		checkInput();
		calculate();
	}

	private void checkInput() {
		if (codeRange != slocMetric.getCodeRange()) {
			throw new IllegalArgumentException("Code ranges must be same!!!");
		}
		if (codeRange != mcCabeMetric.getCodeRange()) {
			throw new IllegalArgumentException("Code ranges must be same!!!");
		}
		if (codeRange != halsteadMetric.getCodeRange()) {
			throw new IllegalArgumentException("Code ranges must be same!!!");
		}
	}

	private void calculate() {
		MIwoc = 171.0 - 5.2 * Math.log(halsteadMetric.get_HV()) - 0.23
				* mcCabeMetric.getCyclomaticNumber() - 16.2
				* Math.log(slocMetric.getPhyLOC() * 100.0 / 171.0);
		MIcw = 50 * Math.sin(Math.sqrt(2.4 * (double) slocMetric.getComLOC()
				/ (double) slocMetric.getPhyLOC()));
		MI = MIwoc + MIcw;
	}

	public void print() {
		System.out.println("MIwoc = " + MIwoc);
		System.out.println("MIcw = " + MIcw);
		System.out.println("MI = " + MI);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.MaintainabilityIndex#getMIWoc()
	 */
	public double getMIWoc() {
		return MIwoc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.MaintainabilityIndex#getMIcw()
	 */
	public double getMIcw() {
		return MIcw;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.MaintainabilityIndex#getMI()
	 */
	public double getMI() {
		return MI;
	}

	public static boolean isSuitable(CodeRange codeRange) {
		return true;
	}

	public CodeRange getCodeRange() {
		return codeRange;
	}

	@Override
	public QualityLevel getQualityLevel() {
		CodeRange range = getCodeRange();
		if ((range.getCodeRangeType() == CodeRangeType.FILE)
				|| (range.getCodeRangeType() == CodeRangeType.CLASS)
				|| (range.getCodeRangeType() == CodeRangeType.ENUMERATION)) {
			if (getMI() > 86) {
				return QualityLevel.HIGH;
			}
			if (getMI() > 65) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.LOW;
		} else if ((range.getCodeRangeType() == CodeRangeType.CONSTRUCTOR)
				|| (range.getCodeRangeType() == CodeRangeType.METHOD)
				|| (range.getCodeRangeType() == CodeRangeType.FUNCTION)
				|| (range.getCodeRangeType() == CodeRangeType.INTERFACE)) {
			if (getMI() > 85) {
				return QualityLevel.HIGH;
			}
			if (getMI() > 65) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.LOW;
		}
		return QualityLevel.HIGH; // not evaluated...
	}

	@Override
	public String getName() {
		return NAME;
	}
}
