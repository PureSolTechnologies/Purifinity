/***************************************************************************
 *
 *   CodeEvaluationSystem.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

public class CodeEvaluationSystem {

    private static boolean evaluateSLOCMetric = true;
    private static boolean evaluateMcCabeMetric = true;
    private static boolean evaluateHalsteadMetric = true;
    private static boolean evaluateEntropyMetric = true;
    private static boolean evaluateMaintainabilityIndex =
	    true;

    public static boolean isEvaluateSLOCMetric() {
	return evaluateSLOCMetric;
    }

    public static boolean isEvaluateMcCabeMetric() {
	return evaluateMcCabeMetric;
    }

    public static boolean isEvaluateHalsteadMetric() {
	return evaluateHalsteadMetric;
    }

    public static boolean isEvaluateEntropyMetric() {
	return evaluateEntropyMetric;
    }

    public static boolean isEvaluateMaintainabilityIndex() {
	return evaluateMaintainabilityIndex;
    }

    public static void setEvaluateSLOCMetric(
	    boolean evaluateSLOCMetric) {
	CodeEvaluationSystem.evaluateSLOCMetric =
		evaluateSLOCMetric;
    }

    public static void setEvaluateMcCabeMetric(
	    boolean evaluateMcCabeMetric) {
	CodeEvaluationSystem.evaluateMcCabeMetric =
		evaluateMcCabeMetric;
    }

    public static void setEvaluateHalsteadMetric(
	    boolean evaluateHalsteadMetric) {
	CodeEvaluationSystem.evaluateHalsteadMetric =
		evaluateHalsteadMetric;
    }

    public static void setEvaluateEntropyMetric(
	    boolean evaluateEntropyMetric) {
	CodeEvaluationSystem.evaluateEntropyMetric =
		evaluateEntropyMetric;
    }

    public static void setEvaluateMaintainabilityIndex(
	    boolean evaluateMaintainabilityIndex) {
	CodeEvaluationSystem.evaluateMaintainabilityIndex =
		evaluateMaintainabilityIndex;
    }

}
