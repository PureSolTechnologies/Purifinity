package com.puresol.coding;

import java.util.ArrayList;
import java.util.List;

import javax.swingx.connect.ConnectionManager;

import org.apache.log4j.Logger;

import com.puresol.osgi.OSGIServiceManager;

/**
 * This is the central programming languages specification manager. All
 * available programming languages should register itself here in this manager.
 * All tools and evaluators ask for supported languages and features within this
 * class.
 * 
 * This class is designed as singleton to avoid data inconsistencies during
 * different initializations.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProgrammingLanguageManager implements
		OSGIServiceManager<ProgrammingLanguage> {

	private static final Logger logger = Logger
			.getLogger(ProgrammingLanguage.class);

	private static ProgrammingLanguageManager instance = null;

	public static ProgrammingLanguageManager getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new ProgrammingLanguageManager();
		}
	}

	private final ConnectionManager connectionManager = ConnectionManager
			.createFor(this);
	private final List<ProgrammingLanguage> languages = new ArrayList<ProgrammingLanguage>();

	/*
	 * Implementation...
	 */

	private ProgrammingLanguageManager() {
	}

	@Override
	public final List<ProgrammingLanguage> getAll() {
		return languages;
	}

	@Override
	public final void register(ProgrammingLanguage language) {
		logger.info("Register programminglanguage '"
				+ language.getClass().getName() + "'...");
		languages.add(language);
	}

	@Override
	public final void unregister(ProgrammingLanguage language) {
		logger.info("Unregister programminglanguage '"
				+ language.getClass().getName() + "'...");
		languages.remove(language);
	}

	@Override
	public void connect(String signal, Object receiver, String slot,
			Class<?>... types) {
		connectionManager.connect(signal, receiver, slot, types);
	}

	@Override
	public void release(String signal, Object receiver, String slot,
			Class<?>... types) {
		connectionManager.release(signal, receiver, slot, types);
	}

	@Override
	public boolean isConnected(String signal, Object receiver, String slot,
			Class<?>... types) {
		return connectionManager.isConnected(signal, receiver, slot, types);
	}
}
