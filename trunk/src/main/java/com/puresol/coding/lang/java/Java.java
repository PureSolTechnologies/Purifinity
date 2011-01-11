package com.puresol.coding.lang.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;

import com.puresol.coding.AbstractProgrammingLanguage;
import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.lang.java.grammar.parts.AnnotationTypeDeclaration;
import com.puresol.coding.lang.java.grammar.parts.ConstructorDeclaration;
import com.puresol.coding.lang.java.grammar.parts.EnumDeclaration;
import com.puresol.coding.lang.java.grammar.parts.MethodDeclaration;
import com.puresol.coding.lang.java.grammar.parts.NormalClassDeclaration;
import com.puresol.coding.lang.java.grammar.parts.NormalInterfaceDeclaration;
import com.puresol.trees.TreeException;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.utils.PersistenceException;

/**
 * This is the base class for Java Programming Language. The lexical and
 * syntactical information were taken out of "The Java™ Language Specification
 * -- Third Edition".
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Java extends AbstractProgrammingLanguage {

	private static final long serialVersionUID = 4685660978239089110L;

	private static final Logger logger = Logger.getLogger(Java.class);

	private static final String[] FILE_SUFFIXES = { ".java" };

	private static Java instance = null;

	public static Java getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new Java();
		}
	}

	private BundleContext bundleContext;

	private Java() {
		super("Java");
	}

	public BundleContext getBundleContext() {
		return bundleContext;
	}

	public void setBundleContext(BundleContext context) {
		this.bundleContext = context;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isObjectOriented() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] getValidFileSuffixes() {
		return FILE_SUFFIXES;
	}

	@Override
	public Analyzer restoreAnalyzer(File file) throws PersistenceException {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					file));
			try {
				return (Analyzer) ois.readObject();
			} finally {
				ois.close();
			}
		} catch (ClassNotFoundException e) {
			/*
			 * In this case the analyzer could not be restored due to missing
			 * classes. This happens with files from another language. We need
			 * to signal this by returning null.
			 */
			return null;
		} catch (FileNotFoundException e) {
			throw new PersistenceException(e);
		} catch (IOException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public Analyzer createAnalyser(File file) {
		return new JavaAnalyser(file);
	}

	@Override
	public List<CodeRange> getAnalyzableCodeRanges(ParserTree parserTree) {
		final List<CodeRange> result = new ArrayList<CodeRange>();
		result.add(new CodeRange("", CodeRangeType.FILE, parserTree));

		TreeWalker<ParserTree> walker = new TreeWalker<ParserTree>(parserTree);
		walker.walk(new TreeVisitor<ParserTree>() {
			@Override
			public WalkingAction visit(ParserTree tree) {
				try {
					if (NormalClassDeclaration.is(tree)) {
						result.add(new NormalClassDeclaration(tree)
								.getCodeRange());
					} else if (EnumDeclaration.is(tree)) {
						result.add(new EnumDeclaration(tree).getCodeRange());
					} else if (NormalInterfaceDeclaration.is(tree)) {
						result.add(new NormalInterfaceDeclaration(tree)
								.getCodeRange());
					} else if (AnnotationTypeDeclaration.is(tree)) {
						result.add(new AnnotationTypeDeclaration(tree)
								.getCodeRange());
					} else if (ConstructorDeclaration.is(tree)) {
						result.add(new ConstructorDeclaration(tree)
								.getCodeRange());
					} else if (MethodDeclaration.is(tree)) {
						result.add(new MethodDeclaration(tree).getCodeRange());
					}
					return WalkingAction.PROCEED;
				} catch (TreeException e) {
					logger.error(e.getMessage(), e);
					return WalkingAction.ABORT;
				}
			}
		});
		return result;
	}

	@Override
	public <T> T getImplementation(Class<T> clazz) {
		try {
			URL url;
			if (bundleContext != null) {
				url = bundleContext.getBundle().getEntry("/config/registry");
			} else {
				url = getClass().getResource("/config/registry");
			}
			Properties properties = new Properties();
			properties.load(url.openStream());
			String className = (String) properties.get(clazz.getName());
			Class<?> clazzz = Class.forName(className);
			Constructor<?> constructor = clazzz.getConstructor();
			@SuppressWarnings("unchecked")
			T t = (T) constructor.newInstance();
			return t;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return null;
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(), e);
			return null;
		} catch (SecurityException e) {
			logger.error(e.getMessage(), e);
			return null;
		} catch (InstantiationException e) {
			logger.error(e.getMessage(), e);
			return null;
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
			return null;
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(), e);
			return null;
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), e);
			return null;
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}
