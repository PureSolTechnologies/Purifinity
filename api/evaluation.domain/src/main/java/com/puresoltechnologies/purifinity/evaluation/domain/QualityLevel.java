package com.puresoltechnologies.purifinity.evaluation.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.commons.math.statistics.Statistics;

/**
 * This class is used for {@link SourceCodeQuality} statistics. It extends
 * {@link Statistics} and uses the possibilties to store several levels to
 * calculate averages and medians.
 * 
 * @author Rick-Rainer Ludwig
 */
public class QualityLevel implements Comparable<QualityLevel>, Serializable {

    private static final long serialVersionUID = -1585502470333567684L;

    /**
     * This method returns a double value as representation for a source
     * quality.
     * 
     * @param quality
     * @return
     */
    public static double getLevel(SourceCodeQuality quality) {
	double level;
	switch (quality) {
	case HIGH:
	    level = 1.0;
	    break;
	case MEDIUM:
	    level = 0.5;
	    break;
	case LOW:
	    level = 0.0;
	    break;
	default:
	    throw new IllegalArgumentException(
		    "Quality '"
			    + quality.name()
			    + "' is not supported or know. The quality level needs to be specfied explicitly. UNSPECIFIED is not supported.");
	}
	return level;
    }

    public static QualityLevel combine(QualityLevel overallQualityLevel,
	    QualityLevel qualityLevel) {
	if (overallQualityLevel == null) {
	    return qualityLevel;
	}
	if (qualityLevel != null) {
	    overallQualityLevel.add(qualityLevel);
	}
	return overallQualityLevel;
    }

    public static QualityLevel valueOf(String value) {
	if (value == null) {
	    return null;
	}
	return new QualityLevel(Double.valueOf(value));
    }

    private final List<Double> levels = new ArrayList<>();
    private Statistics statistics = null;

    public QualityLevel(SourceCodeQuality quality) {
	levels.add(getLevel(quality));
    }

    public QualityLevel(double level) {
	levels.add(level);
    }

    private SourceCodeQuality getQuality(double level) {
	if (level < 0.0) {
	    throw new IllegalArgumentException(
		    "Level needs to be between (and including) 0.0 and 1.0!");
	}
	if (level <= 1.0 / 3.0) {
	    return SourceCodeQuality.LOW;
	}
	if (level < 2.0 / 3.0) {
	    return SourceCodeQuality.MEDIUM;
	}
	if (level <= 1.0) {
	    return SourceCodeQuality.HIGH;
	}
	throw new IllegalArgumentException(
		"Level needs to be between (and including) 0.0 and 1.0!");
    }

    public double getLevel() {
	assureCalculatedStatistics();
	return statistics.getAvg();
    }

    public double getDeviation() {
	assureCalculatedStatistics();
	return statistics.getStdDev();
    }

    private void assureCalculatedStatistics() {
	if (statistics == null) {
	    statistics = new Statistics(levels);
	}
    }

    public void add(SourceCodeQuality quality) {
	if (quality == null) {
	    throw new IllegalArgumentException("Quality must not be null.");
	}
	if (quality == SourceCodeQuality.UNSPECIFIED) {
	    throw new IllegalArgumentException(
		    "Quality must not be unspecified.");
	}
	statistics = null;
	levels.add(getLevel(quality));
    }

    public void add(QualityLevel level) {
	if (level == null) {
	    throw new IllegalArgumentException(
		    "Quality level must not be null.");
	}
	statistics = null;
	levels.addAll(level.levels);
    }

    public SourceCodeQuality getQuality() {
	return getQuality(getLevel());
    }

    @Override
    public String toString() {
	return String.valueOf(getLevel());
    }

    public QualityLevel combine(QualityLevel qualityLevel) {
	return combine(this, qualityLevel);
    }

    @Override
    public int compareTo(QualityLevel o) {
	return Double.valueOf(getLevel()).compareTo(o.getLevel());
    }

}
