package com.puresol.coding.store.fs.analysis;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeStore;
import com.puresol.coding.analysis.api.CodeStoreException;
import com.puresol.coding.analysis.api.CodeStoreFactory;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.utils.HashId;

public class AnalysisRunCallable implements Callable<AnalyzedCode> {

    private final Logger logger = LoggerFactory
	    .getLogger(AnalysisRunCallable.class);

    private static final CodeStoreFactory codeStoreFactory = CodeStoreFactory
	    .getFactory();

    private final CodeLocation sourceFile;
    private final CodeStore codeStore = codeStoreFactory.getInstance();

    public AnalysisRunCallable(CodeLocation sourceFile) {
	super();
	this.sourceFile = sourceFile;
    }

    @Override
    public AnalyzedCode call() throws IOException, CodeStoreException {
	logger.info("Starting analysis for '" + sourceFile + "'...");
	InputStream stream = sourceFile.openStream();
	HashId hashId;
	try {
	    hashId = codeStore.storeRawFile(stream);
	} finally {
	    stream.close();
	}
	AnalyzedCode result = analyzeCode(hashId, sourceFile);
	logger.info("Finished analysis for '" + sourceFile + "'.");
	return result;
    }

    /**
     * This method analyzes a single file. The file is added to faildFiles if
     * there was a analyzer found, but the analyzer had issues to analyze the
     * file, which might have two reasons:
     * 
     * 1) The file is not valid.
     * 
     * 2) The analyzer is buggy.
     * 
     * In either way, these files needs to be tracked and recorded.
     * 
     * @param file
     *            is the file to be analyzed.
     */
    private AnalyzedCode analyzeCode(HashId hashId, CodeLocation sourceFile) {
	try {
	    if (codeStore.isAvailable(hashId) && codeStore.wasAnalyzed(hashId)) {
		CodeAnalysis analysis = codeStore.loadAnalysis(hashId);
		return new AnalyzedCode(hashId, sourceFile,
			analysis.getStartTime(), analysis.getDuration(),
			analysis.getLanguageName(),
			analysis.getLanguageVersion());
	    } else {
		CodeAnalyzerImpl fileAnalyzer = new CodeAnalyzerImpl(
			sourceFile, hashId);
		fileAnalyzer.analyze();
		if (fileAnalyzer.isAnalyzed()) {
		    codeStore.storeAnalysis(hashId, fileAnalyzer.getAnalyzer()
			    .getAnalysis());
		    return fileAnalyzer.getAnalysis().getAnalyzedFile();
		} else {
		    logger.warn("File "
			    + sourceFile.getHumanReadableLocationString()
			    + " could be analyzed.");
		    return new AnalyzedCode(hashId, sourceFile, null, 0, null,
			    null);
		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new AnalyzedCode(hashId, sourceFile, null, 0, null, null);
	}
    }

}
