package com.puresol.purifinity.uhura.ust;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;

import com.puresol.purifinity.uhura.parser.ParserTree;

public class USTCreatorImpl implements USTCreator {

	private final Package pkg;

	private final Map<String, USTCreator> classes = new HashMap<String, USTCreator>();

	public USTCreatorImpl(Package pkg)
			throws UniversalSyntaxTreeCreatorException {
		this.pkg = pkg;
		scanPackage();
	}

	private void scanPackage() throws UniversalSyntaxTreeCreatorException {
		Set<Class<? extends USTCreator>> types = getAllUSTCreatorClasses();
		for (Class<?> clazz : types) {
			if (clazz.equals(getClass())) {
				// Exclude self...
				continue;
			}
			instantiateAndStore(clazz);
		}
	}

	private Set<Class<? extends USTCreator>> getAllUSTCreatorClasses() {
		String packageName = pkg.getName();
		Reflections reflections = new Reflections(
				ClasspathHelper.forPackage(packageName));
		Set<Class<? extends USTCreator>> types = reflections
				.getSubTypesOf(USTCreator.class);
		return types;
	}

	private void instantiateAndStore(Class<?> clazz)
			throws UniversalSyntaxTreeCreatorException {
		String simpleName = clazz.getSimpleName();
		try {
			classes.put(simpleName, (USTCreator) clazz.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			throw new UniversalSyntaxTreeCreatorException(
					"Could not instantiate USTCreator class '" + simpleName
							+ "'.", e);
		}
	}

	@Override
	public UniversalSyntaxTree createUST(ParserTree parserTree) {
		String nodeName = parserTree.getName();
		String className = NameTranslator.getProductionClassName(nodeName)
				+ "Creator";
		return classes.get(className).createUST(parserTree);
	}
}
