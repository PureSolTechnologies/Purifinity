package com.puresol.purifinity.uhura.ust;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;

import com.puresol.commons.trees.TreeVisitor;
import com.puresol.commons.trees.TreeWalker;
import com.puresol.commons.trees.WalkingAction;
import com.puresol.purifinity.uhura.parser.ParserTree;

public class USTCreatorImpl implements USTCreator {

	private final Package pkg;

	private final Map<String, Class<?>> classes = new HashMap<String, Class<?>>();

	public USTCreatorImpl(Package pkg) {
		this.pkg = pkg;
		scanPackage();
	}

	private void scanPackage() {
		String packageName = pkg.getName();
		Reflections reflections = new Reflections(
				ClasspathHelper.forPackage(packageName));
		Set<Class<? extends Production>> types = reflections
				.getSubTypesOf(Production.class);
		for (Class<?> clazz : types) {
			classes.put(clazz.getSimpleName(), clazz);
		}
	}

	@Override
	public CompilationUnit createUST(ParserTree parserTree) {
		TreeWalker.walk(new TreeVisitor<ParserTree>() {

			@Override
			public WalkingAction visit(ParserTree node) {
				String nodeName = node.getName();
				String className = NameTranslator
						.getProductionClassName(nodeName);
				Class<?> clazz = classes.get(className);
				return WalkingAction.PROCEED;
			}
		}, parserTree);
		return null;
	}
}
