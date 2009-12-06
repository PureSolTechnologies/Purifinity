package com.puresol.coding.antlr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ANTLRJavaHelper extends ANTLRHelper {

	private static final Logger logger = Logger
			.getLogger(ANTLRJavaHelper.class);

	private String packageName = "unknown";
	private List<String> imports = new ArrayList<String>();
	private List<JavaClass> classes = new ArrayList<JavaClass>();
	private List<JavaMethod> methods = new ArrayList<JavaMethod>();

	public ANTLRJavaHelper() {
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
		logger.debug("package " + packageName);
	}

	public void addImport(String importName) {
		imports.add(importName);
		logger.debug("import " + importName);
	}

	public List<String> getImports() {
		return imports;
	}

	public void addClass(String name, String modifiers, String extended,
			String implemented) {
		JavaClass javaClass = new JavaClass(name, modifiers, extended,
				implemented);
		classes.add(javaClass);
		logger.debug("class: " + javaClass.toString());
	}

	public List<JavaClass> getClasses() {
		return classes;
	}

	public void addMethod(String name, String modifiers) {
		JavaMethod javaMethod = new JavaMethod(name, modifiers);
		methods.add(javaMethod);
		logger.debug("method: " + javaMethod.toString());
	}

	public List<JavaMethod> getMethods() {
		return methods;
	}
}
