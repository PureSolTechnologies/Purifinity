package com.puresol.coding.lang.fortran;

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
import com.puresol.trees.TreeException;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.utils.PersistenceException;

public class Fortran extends AbstractProgrammingLanguage {

	private static final Logger logger = Logger.getLogger(Fortran.class);

	private static final String[] FILE_SUFFIXES = { ".f", ".f77", ".f90",
			".f95", ".for" };

	private static Fortran instance = null;

	public static Fortran getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new Fortran();
		}
	}

	private SourceForm sourceForm = SourceForm.FREE_FORM;
	private BundleContext bundleContext;

	private Fortran() {
		super("Fortran");
	}

	public BundleContext getBundleContext() {
		return bundleContext;
	}

	public void setBundleContext(BundleContext context) {
		this.bundleContext = context;
	}

	@Override
	public boolean isObjectOriented() {
		return false;
	}

	@Override
	protected String[] getValidFileSuffixes() {
		return FILE_SUFFIXES;
	}

	@Override
	public Analyzer restoreAnalyzer(File file) throws PersistenceException {
		try {
			ObjectInputStream ois = null;
			ois = new ObjectInputStream(new FileInputStream(file));
			try {
				return (Analyzer) ois.readObject();
			} finally {
				ois.close();
			}
		} catch (ClassNotFoundException e) {
			return null;
		} catch (FileNotFoundException e) {
			throw new PersistenceException(e);
		} catch (IOException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public Analyzer createAnalyser(File file) {
		return new FortranAnalyser(file);
	}

	@Override
	public List<CodeRange> getAnalyzableCodeRanges(ParserTree parserTree) {
		TreeWalker<ParserTree> walker = new TreeWalker<ParserTree>(parserTree);
		final List<CodeRange> result = new ArrayList<CodeRange>();
		walker.walk(new TreeVisitor<ParserTree>() {

			@Override
			public WalkingAction visit(ParserTree tree) {
				try {
					if ("main-program".equals(tree.getName())) {
						String name;
						name = tree.getChild("program-stmt")
								.getChildren("NAME_LITERAL").get(1).getText();
						result.add(new CodeRange(name, CodeRangeType.PROGRAM,
								tree));
					} else if ("function-subprogram".equals(tree.getName())) {
						String name = tree.getChild("function-stmt")
								.getChildren("NAME_LITERAL").get(1).getText();
						result.add(new CodeRange(name, CodeRangeType.FUNCTION,
								tree));
					} else if ("subroutine-subprogram".equals(tree.getName())) {
						String name = tree.getChild("subroutine-stmt")
								.getChildren("NAME_LITERAL").get(1).getText();
						result.add(new CodeRange(name,
								CodeRangeType.SUBROUTINE, tree));
					} else if ("module".equals(tree.getName())) {
						String name = tree.getChild("module-stmt")
								.getChildren("NAME_LITERAL").get(1).getText();
						result.add(new CodeRange(name, CodeRangeType.MODULE,
								tree));
					} else if ("submodule".equals(tree.getName())) {
						String name = tree.getChild("submodule-stmt")
								.getChildren("NAME_LITERAL").get(1).getText();
						result.add(new CodeRange(name, CodeRangeType.MODULE,
								tree));
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

	public void setSourceForm(SourceForm sourceForm) {
		this.sourceForm = sourceForm;
	}

	public SourceForm getSourceForm() {
		return sourceForm;
	}
}
