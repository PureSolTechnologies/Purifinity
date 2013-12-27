package com.puresoltechnologies.purifinity.framework.analysis.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzerException;
import com.puresoltechnologies.purifinity.analysis.domain.AnalyzedCode;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.framework.store.api.FileStore;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreFactory;

public class AnalysisRunCallable implements Callable<AnalyzedCode> {

	private final Logger logger = LoggerFactory
			.getLogger(AnalysisRunCallable.class);

	private static final FileStoreFactory codeStoreFactory = FileStoreFactory
			.getFactory();

	private final SourceCodeLocation sourceFile;
	private final FileStore codeStore = codeStoreFactory.getInstance();

	public AnalysisRunCallable(SourceCodeLocation sourceFile) {
		super();
		this.sourceFile = sourceFile;
	}

	@Override
	public AnalyzedCode call() throws IOException, FileStoreException {
		logger.info("Starting analysis for '" + sourceFile + "'...");
		HashId hashId = storeRawFile();
		AnalyzedCode result = analyzeCode(hashId, sourceFile);
		logger.info("Finished analysis for '" + sourceFile + "'.");
		return result;
	}

	/**
	 * This method stores the raw file within the code store for later
	 * reference.
	 * 
	 * @return A {@link HashId} is returned as reference to the stored file.
	 * @throws IOException
	 * @throws FileStoreException
	 */
	private HashId storeRawFile() throws IOException, FileStoreException {
		InputStream stream = sourceFile.openStream();
		try {
			return codeStore.storeRawFile(stream);
		} finally {
			stream.close();
		}
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
	private AnalyzedCode analyzeCode(HashId hashId, SourceCodeLocation sourceFile) {
		try {
			if (codeStore.wasAnalyzed(hashId)) {
				return loadAnalysis(hashId, sourceFile);
			} else {
				return createNewAnalysis(hashId, sourceFile);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Throwable t = e;
			StringBuilder message = new StringBuilder();
			int id = 1;
			while (t != null) {
				message.append(id).append(")\t");
				message.append(t.getMessage());
				message.append("\n");
				t = t.getCause();
				id++;
			}
			return new AnalyzedCode(hashId, sourceFile, null, 0, null, null,
					message.toString());
		}
	}

	/**
	 * This method creates a new analysis.
	 * 
	 * @param hashId
	 * @param sourceFile
	 * @return
	 * @throws AnalyzerException
	 * @throws IOException
	 * @throws FileStoreException
	 */
	private AnalyzedCode createNewAnalysis(HashId hashId,
			SourceCodeLocation sourceFile) throws AnalyzerException, IOException,
			FileStoreException {
		CodeAnalyzerImpl fileAnalyzer = new CodeAnalyzerImpl(sourceFile, hashId);
		fileAnalyzer.analyze();
		if (fileAnalyzer.isAnalyzed()) {
			codeStore.storeAnalysis(hashId, fileAnalyzer.getAnalyzer()
					.getAnalysis());
			return fileAnalyzer.getAnalysis().getAnalyzedFile();
		} else {
			logger.warn("File " + sourceFile.getHumanReadableLocationString()
					+ " could be analyzed.");
			return new AnalyzedCode(hashId, sourceFile, null, 0, null, null);
		}
	}

	/**
	 * This method loads an already existing analysis.
	 * 
	 * @param hashId
	 * @param sourceFile
	 * @return
	 * @throws FileStoreException
	 */
	private AnalyzedCode loadAnalysis(HashId hashId, SourceCodeLocation sourceFile)
			throws FileStoreException {
		CodeAnalysis analysis = codeStore.loadAnalysis(hashId);
		AnalyzedCode analyzedCode = new AnalyzedCode(hashId, sourceFile,
				analysis.getStartTime(), analysis.getDuration(),
				analysis.getLanguageName(), analysis.getLanguageVersion());
		return analyzedCode;
	}

}