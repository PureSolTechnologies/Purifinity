package com.puresol.purifinity.uhura.ust;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;

import com.google.common.collect.Multimap;
import com.puresol.commons.trees.TreeException;
import com.puresol.purifinity.uhura.parser.ParserTree;

public class USTCreatorImpl implements USTCreator {

	private final Package pkg;

	private final Map<String, USTCreator> classes = new HashMap<String, USTCreator>();

	public USTCreatorImpl(Class<? extends USTCreator> rootUSTNodeCreator)
			throws UniversalSyntaxTreeCreatorException {
		this.pkg = rootUSTNodeCreator.getPackage();
		scanPackage(rootUSTNodeCreator.getClassLoader());
	}

	private void scanPackage(ClassLoader classLoader)
			throws UniversalSyntaxTreeCreatorException {
		Set<Class<? extends USTCreator>> types = getAllUSTCreatorClasses(classLoader);
		for (Class<?> clazz : types) {
			if (clazz.equals(getClass())) {
				// Exclude self...
				continue;
			}
			if (Modifier.isAbstract(clazz.getModifiers())) {
				// Abstract classes cannot be instantiated...
				continue;
			}
			instantiateAndStore(clazz);
		}
	}

	private Set<Class<? extends USTCreator>> getAllUSTCreatorClasses(
			ClassLoader classLoader) {
		String packageName = pkg.getName();
		Reflections reflections = new Reflections(
				ClasspathHelper.forPackage(packageName),
				new RecursiveSubTypesScanner(), classLoader);
		Multimap<String, String> map = reflections.getStore().get(
				RecursiveSubTypesScanner.class);
		Collection<String> creatorNames = map.get(USTCreator.class.getName());
		Set<Class<? extends USTCreator>> creatorClasses = new HashSet<>();
		for (String creatorName : creatorNames) {
			try {
				@SuppressWarnings("unchecked")
				Class<? extends USTCreator> creatorClass = (Class<? extends USTCreator>) Class
						.forName(creatorName);
				creatorClasses.add(creatorClass);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(
						"Could not scan for USTCreator classes.", e);
			}
		}
		return creatorClasses;
	}

	private void instantiateAndStore(Class<?> clazz)
			throws UniversalSyntaxTreeCreatorException {
		String simpleName = clazz.getSimpleName();
		try {
			Constructor<?> constructor = clazz.getConstructor(USTCreator.class);
			classes.put(simpleName, (USTCreator) constructor.newInstance(this));
		} catch (InstantiationException | IllegalAccessException
				| NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException e) {
			throw new UniversalSyntaxTreeCreatorException(
					"Could not instantiate USTCreator class '" + simpleName
							+ "'.", e);
		}
	}

	@Override
	public UniversalSyntaxTree createUST(ParserTree parserTree) throws TreeException {
		String nodeName = parserTree.getName();
		String className = NameTranslator.getProductionClassName(nodeName)
				+ "Creator";
		USTCreator creator = classes.get(className);
		if (creator == null) {
			throw new UniversalSyntaxTreeCreatorException(
					"Could not find a UST creator implementation for '"
							+ nodeName + "' with name '" + className + "'!");
		}
		return creator.createUST(parserTree);
	}
}
