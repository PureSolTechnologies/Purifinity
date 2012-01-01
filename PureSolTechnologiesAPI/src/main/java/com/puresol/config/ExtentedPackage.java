/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.config;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class ExtentedPackage {

	public static Class<?>[] getClasses(Package pack)
			throws ClassNotFoundException {
		return getClasses(pack.getName());
	}

	public static Class<?>[] getClasses(String packageName)
			throws ClassNotFoundException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		if (classLoader == null) {
			throw new ClassNotFoundException("Can't get class loader.");
		}
		String path = packageName.replace('.', '/');
		URL resource = classLoader.getResource(path);
		if (resource == null) {
			throw new ClassNotFoundException("No resource for " + path);
		}
		File directory = new File(resource.getFile());
		if (!directory.exists()) {
			throw new ClassNotFoundException(packageName
					+ " does not appear to be a valid package");
		}
		return getClassesFromDirectory(packageName, directory);
	}

	static private Class<?>[] getClassesFromDirectory(String packageName,
			File directory) throws ClassNotFoundException {
		String[] files = directory.list();
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		for (int i = 0; i < files.length; i++) {
			if (!files[i].endsWith(".class")) {
				continue;
			}
			Class<?> clazz = Class.forName(packageName + '.'
					+ files[i].substring(0, files[i].length() - 6));
			if (clazz.isInterface()) {
				continue;
			}
			classes.add(clazz);
		}
		Class<?>[] classesArray = new Class[classes.size()];
		classes.toArray(classesArray);
		return classesArray;
	}

	public static Class<?>[] getClassesWithSuperclass(Package pack,
			Class<?> superclass) throws ClassNotFoundException {
		return getClassesWithSuperclass(pack.getName(), superclass);
	}

	public static Class<?>[] getClassesWithSuperclass(String packageName,
			Class<?> superclass) throws ClassNotFoundException {
		Class<?>[] classes = getClasses(packageName);
		classes = filterClassesWithSuperclass(classes, superclass);
		return classes;
	}

	static private Class<?>[] filterClassesWithSuperclass(Class<?>[] classes,
			Class<?> superclass) {
		ArrayList<Class<?>> classArrayList = new ArrayList<Class<?>>();
		for (int index = 0; index < classes.length; index++) {
			Class<?> clazz = classes[index];
			if (!clazz.getSuperclass().equals(superclass)) {
				continue;
			}
			classArrayList.add(clazz);
		}
		classes = new Class[classArrayList.size()];
		classArrayList.toArray(classes);
		return classes;
	}

	public static Class<?>[] getClassesWithInterface(Package pack,
			Class<?> interfce) throws ClassNotFoundException {
		return getClassesWithInterface(pack.getName(), interfce);
	}

	public static Class<?>[] getClassesWithInterface(String packageName,
			Class<?> interfce) throws ClassNotFoundException {
		if (!interfce.isInterface()) {
			throw new ClassNotFoundException("Class '" + interfce.getName()
					+ "' is not an interface!");
		}
		Class<?>[] classes = getClasses(packageName);
		classes = filterClassesWithInterface(classes, interfce);
		return classes;
	}

	static private Class<?>[] filterClassesWithInterface(Class<?>[] classes,
			Class<?> interfce) {
		ArrayList<Class<?>> classArrayList = new ArrayList<Class<?>>();
		for (int index = 0; index < classes.length; index++) {
			Class<?> clazz = classes[index];
			if (!interfce.isAssignableFrom(clazz)) {
				continue;
			}
			classArrayList.add(clazz);
		}
		classes = new Class[classArrayList.size()];
		classArrayList.toArray(classes);
		return classes;
	}
}
