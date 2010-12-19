package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swingx.config.ClassRegistry;

import org.apache.log4j.Logger;

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

	private Fortran() {
		super("Fortran");
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
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			Analyzer analyzer = (Analyzer) ois.readObject();
			ois.close();
			return analyzer;
		} catch (Throwable e) {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e1) {
				}
			}
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
								.getChild("NAME_LITERAL").getText();
						result.add(new CodeRange(name,
								CodeRangeType.SUBROUTINE, tree));
					} else if ("function-subprogram".equals(tree.getName())) {
						String name = tree.getChild("function-stmt")
								.getChild("NAME_LITERAL").getText();
						result.add(new CodeRange(name,
								CodeRangeType.SUBROUTINE, tree));
					} else if ("subroutine-subprogram".equals(tree.getName())) {
						String name = tree.getChild("subroutine-stmt")
								.getChild("NAME_LITERAL").getText();
						result.add(new CodeRange(name,
								CodeRangeType.SUBROUTINE, tree));

					} else if ("module".equals(tree.getName())) {
						String name = tree.getChild("module-stmt")
								.getChild("NAME_LITERAL").getText();
						result.add(new CodeRange(name,
								CodeRangeType.SUBROUTINE, tree));
					} else if ("submodule".equals(tree.getName())) {
						String name = tree.getChild("submodule-stmt")
								.getChild("NAME_LITERAL").getText();
						result.add(new CodeRange(name,
								CodeRangeType.SUBROUTINE, tree));
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
		return ClassRegistry.create(clazz);
	}
}
