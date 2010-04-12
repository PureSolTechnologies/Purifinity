package com.puresol.coding.evaluator;

import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.metric.Metric;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;
import com.puresol.utils.Property;

/**
 * This singleton class is the central manager for all metrics and evaluators
 * which can be used for code evaluation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EvaluatorManager {

    private static final Logger logger = Logger
	    .getLogger(EvaluatorManager.class);

    private static EvaluatorManager instance = null;

    public static Evaluator createEvaluatorInstance(
	    Class<? extends Evaluator> evaluatorClass,
	    ProjectAnalyser projectAnalyser) throws ClassInstantiationException {
	return Instances.createInstance(evaluatorClass, projectAnalyser);
    }

    public static EvaluatorManager getInstance() {
	if (instance == null) {
	    createInstance();
	}
	return instance;
    }

    private static synchronized void createInstance() {
	if (instance == null) {
	    instance = new EvaluatorManager();
	}
    }

    private final ArrayList<Class<? extends Metric>> metricClasses = new ArrayList<Class<? extends Metric>>();
    private final ArrayList<Class<? extends Evaluator>> evaluatorClasses = new ArrayList<Class<? extends Evaluator>>();

    private final Hashtable<Class<?>, String> names = new Hashtable<Class<?>, String>();
    private final Hashtable<Class<?>, String> descriptions = new Hashtable<Class<?>, String>();

    private EvaluatorManager() {
	initStatics();
    }

    private void initStatics() {
	initMetrics();
	initEvaluators();
    }

    private void initMetrics() {
	for (String analyser : CodeEvaluationProperties.getPropertyValue(
		"CodeEvaluation.Metrics").split(",")) {
	    Class<? extends Metric> clazz = getMetricForName(analyser);
	    if (clazz != null) {
		metricClasses.add(clazz);
		registerName(clazz);
		registerDescription(clazz);
		registerSupportedProperties(clazz);
	    }
	}
    }

    private Class<? extends Metric> getMetricForName(String metric) {
	try {
	    @SuppressWarnings("unchecked")
	    Class<? extends Metric> clazz = (Class<? extends Metric>) Class
		    .forName(metric);
	    return clazz;
	} catch (ClassNotFoundException e) {
	    logger.error("Class '" + metric
		    + "' was not found, but set in CodeAnalysis.properties!");
	}
	return null;
    }

    private void initEvaluators() {
	for (String evaluator : CodeEvaluationProperties.getPropertyValue(
		"CodeEvaluation.Evaluators").split(",")) {
	    Class<? extends Evaluator> clazz = getEvaluatorForName(evaluator);
	    if (clazz != null) {
		logger.info("Initialize Evaluator '" + clazz.getName() + "'");
		evaluatorClasses.add(clazz);
		registerName(clazz);
		registerDescription(clazz);
		registerSupportedProperties(clazz);
	    }
	}
    }

    @SuppressWarnings("unchecked")
    private Class<? extends Evaluator> getEvaluatorForName(String evaluator) {
	try {
	    return (Class<? extends Evaluator>) Class.forName(evaluator);
	} catch (ClassNotFoundException e) {
	    logger.error("Class '" + evaluator
		    + "' was not found, but set in CodeAnalysis.properties!");
	}
	return null;
    }

    public ArrayList<Class<? extends Metric>> getMetricClasses() {
	return metricClasses;
    }

    public ArrayList<Class<? extends Evaluator>> getEvaluatorClasses() {
	return evaluatorClasses;
    }

    private void registerName(Class<?> clazz) {
	try {
	    logger.info("Register name for '" + clazz.getName() + "'");
	    names.put(clazz, (String) clazz.getField("NAME").get(null));
	    logger.info("Name: '" + getName(clazz) + "'");
	    return;
	} catch (SecurityException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoSuchFieldException e) {
	}
	logger.warn("Setting defaults...");
	names.put(clazz, clazz.getSimpleName());
    }

    private void registerDescription(Class<?> clazz) {
	try {
	    logger.info("Register description for '" + clazz.getName() + "'");
	    descriptions.put(clazz, (String) clazz.getField("DESCRIPTION").get(
		    null));
	    logger.info("Description: '" + getDescription(clazz) + "'");
	    return;
	} catch (SecurityException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoSuchFieldException e) {
	}
	logger.warn("Setting defaults...");
	descriptions.put(clazz, clazz.getName());
    }

    @SuppressWarnings("unchecked")
    private void registerSupportedProperties(Class<?> clazz) {
	try {
	    logger.info("Register properties for '" + clazz.getName() + "'");
	    CodeEvaluationProperties codeEvaluationProperties = CodeEvaluationProperties
		    .getInstance();
	    ArrayList<Property> properties = (ArrayList<Property>) clazz
		    .getField("SUPPORTED_PROPERTIES").get(null);
	    codeEvaluationProperties.registerProperties(properties);
	    logger.info("Properties:");
	    for (Property property : properties) {
		logger.info(property.toString());
	    }
	    return;
	} catch (SecurityException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoSuchFieldException e) {
	}
    }

    public String getName(Class<?> clazz) {
	return names.get(clazz);
    }

    public String getDescription(Class<?> clazz) {
	return descriptions.get(clazz);
    }
}
