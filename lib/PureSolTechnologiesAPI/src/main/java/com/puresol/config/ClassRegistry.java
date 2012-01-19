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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a central register for applications to register classes for
 * interfaces. This is used to decouple dependencies.
 * 
 * The configuration is done in a configuration file named "config/registry",
 * where the leading path is build by ConfigFile.getPotentialConfigFiles().
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ClassRegistry {

    static private final Logger logger = LoggerFactory
	    .getLogger(ClassRegistry.class);

    static public final String REGISTRY_FILE = "config/registry";

    static private Map<String, ClassRegistryElement> register = new HashMap<String, ClassRegistryElement>();
    static private Map<String, Object> classes = new HashMap<String, Object>();

    static public void register(Class<?> interfce, ClassRegistryElement element) {
	register.put(interfce.getName(), element);
    }

    static public void register(Class<?> interfce) {
	try {
	    logger.info("Register interface '" + interfce.getName() + "'");
	    String className = Configurator.getEntry(REGISTRY_FILE,
		    interfce.getName(), "class", true);
	    logger.debug("class: '" + className + "'");
	    String typeName = Configurator.getEntry(REGISTRY_FILE,
		    interfce.getName(), "type", true);
	    logger.debug("type: '" + typeName + "'");
	    ClassRegistryElementType type = ClassRegistryElementType
		    .from(typeName);
	    register(interfce, new ClassRegistryElement(type, className));
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	}
    }

    static public boolean isRegistered(Class<?> interfce) {
	return (register.containsKey(interfce.getName()));
    }

    static public void unregister(Class<?> interfce) {
	register.remove(interfce.getName());
    }

    static public <T> T create(Class<T> interfce) {
	logger.debug("create for interface '" + interfce.getName() + "'");
	if (!isRegistered(interfce)) {
	    register(interfce);
	    if (!isRegistered(interfce)) {
		return null;
	    }
	}
	ClassRegistryElement element = register.get(interfce.getName());
	if (element.getType() == ClassRegistryElementType.SINGLETON) {
	    return singleton(interfce, element);
	} else if (element.getType() == ClassRegistryElementType.CLONED) {
	    return cloned(interfce, element);
	}
	return factory(interfce, element);
    }

    static private <T> T factory(Class<T> interfce, ClassRegistryElement element) {
	try {
	    @SuppressWarnings("unchecked")
	    Class<T> clazz = (Class<T>) Class.forName(element.getClassName());
	    return clazz.getConstructor().newInstance();
	} catch (ClassNotFoundException e) {
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
	}
    }

    static private <T> T singleton(Class<T> interfce,
	    ClassRegistryElement element) {
	if (!classes.containsKey(interfce.getName())) {
	    createSingleton(interfce, element);
	}
	@SuppressWarnings("unchecked")
	T result = (T) classes.get(interfce.getName());
	return result;
    }

    /**
     * This method is used for a synchronized creation of the singleton
     * instance. The instance should be created here and not in singleton() to
     * avoid a permanent synchronized call to get the singleton instance to
     * avoid a speed issue.
     * 
     * @param interfce
     * @param element
     */
    static private synchronized void createSingleton(Class<?> interfce,
	    ClassRegistryElement element) {
	if (!classes.containsKey(interfce.getName())) {
	    classes.put(interfce.getName(), factory(interfce, element));
	}
    }

    static private <T> T cloned(Class<T> interfce, ClassRegistryElement element) {
	try {
	    if (!classes.containsKey(interfce.getName())) {
		classes.put(interfce.getName(), factory(interfce, element));
	    }
	    Object o = classes.get(interfce.getName());
	    if (o == null) {
		return null;
	    }
	    if (Cloneable.class.isAssignableFrom(interfce)) {
		Method clone = o.getClass().getMethod("clone");
		@SuppressWarnings("unchecked")
		T result = (T) clone.invoke(o);
		return result;
	    } else {
		return factory(interfce, element);
	    }
	} catch (SecurityException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	} catch (NoSuchMethodException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	} catch (InvocationTargetException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	}
    }
}
