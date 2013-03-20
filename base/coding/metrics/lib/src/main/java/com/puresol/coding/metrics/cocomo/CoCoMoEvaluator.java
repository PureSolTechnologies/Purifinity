/***************************************************************************
 *
 *   CoCoMo.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.metrics.cocomo;

import java.util.List;
import java.util.Vector;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.coding.evaluation.impl.AbstractEvaluator;
import com.puresol.coding.metrics.sloc.SLOCEvaluator;
import com.puresol.coding.metrics.sloc.SLOCFileResult;
import com.puresol.coding.metrics.sloc.SLOCFileResults;
import com.puresol.utils.HashId;

/**
 * This class calculates the CoCoMo for a set number of sloc and a given average
 * salary and complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CoCoMoEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = 5098378023541671490L;

    public static final String NAME = "COst COnstruction MOdel";

    public static final String DESCRIPTION = "The COst COnstruction MOdel is a simple way "
	    + "to estimate the construction costs of a "
	    + "software project by couting the physical lines of code.";

    public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new Vector<QualityCharacteristic>();

    private final EvaluatorStore store;
    private final EvaluatorStore slocStore;
    private Complexity complexity = Complexity.LOW;
    private int averageSalary = 56286;
    private String currency = "USD";

    public CoCoMoEvaluator(AnalysisRun analysisRun, HashIdFileTree path) {
	super(NAME, DESCRIPTION, analysisRun, path);
	store = getEvaluatorStore();
	slocStore = EvaluatorStoreFactory.getFactory().createInstance(
		SLOCEvaluator.class);
    }

    public void setComplexity(Complexity complexity) {
	this.complexity = complexity;
    }

    public void setAverageSalary(int averageSalary, String currency) {
	this.averageSalary = averageSalary;
	this.currency = currency;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected void processFile(CodeAnalysis analysis) {
	HashId hashId = analysis.getAnalyzedFile().getHashId();
	if (slocStore.hasFileResults(hashId)) {
	    SLOCFileResults slocFileResults = (SLOCFileResults) slocStore
		    .readFileResults(hashId);
	    for (SLOCFileResult results : slocFileResults) {
		if (results.getCodeRangeType() == CodeRangeType.FILE) {
		    int phyLoc = results.getSLOCResult().getPhyLOC();
		    CoCoMoValueSet fileResults = new CoCoMoValueSet();
		    fileResults.setAverageSalary(averageSalary, currency);
		    fileResults.setComplexity(complexity);
		    fileResults.setSloc(phyLoc);
		    store.storeFileResults(hashId, fileResults);
		    break;
		}
	    }
	}
    }

    @Override
    protected void processDirectory(HashIdFileTree directory)
	    throws InterruptedException {
	int phyLoc = 0;
	for (HashIdFileTree child : directory.getChildren()) {
	    HashId hashId = child.getHashId();
	    if (child.isFile()) {
		if (store.hasFileResults(hashId)) {
		    CoCoMoValueSet fileResults = (CoCoMoValueSet) store
			    .readFileResults(hashId);
		    phyLoc += fileResults.getPhyLOC();
		}
	    } else {
		if (store.hasDirectoryResults(hashId)) {
		    CoCoMoValueSet directoryResults = (CoCoMoValueSet) store
			    .readDirectoryResults(hashId);
		    phyLoc += directoryResults.getPhyLOC();
		}
	    }
	}
	CoCoMoValueSet directoryResults = new CoCoMoValueSet();
	directoryResults.setAverageSalary(averageSalary, currency);
	directoryResults.setComplexity(complexity);
	directoryResults.setSloc(phyLoc);
	store.storeDirectoryResults(directory.getHashId(), directoryResults);
    }

    @Override
    protected void processProject() throws InterruptedException {
	// TODO Auto-generated method stub
    }

}
