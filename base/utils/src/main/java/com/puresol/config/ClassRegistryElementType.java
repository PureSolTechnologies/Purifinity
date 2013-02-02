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

public enum ClassRegistryElementType {

    FACTORY("factory"), SINGLETON("singleton"), CLONED("cloned");

    private String name;

    private ClassRegistryElementType(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public boolean is(String name) {
	return (this.name.equalsIgnoreCase(name));
    }

    public static ClassRegistryElementType from(String name) {
	ClassRegistryElementType[] types =
		ClassRegistryElementType.class.getEnumConstants();
	for (ClassRegistryElementType type : types) {
	    if (type.is(name)) {
		return type;
	    }
	}
	throw new IllegalArgumentException(
		"'"
			+ name
			+ "' is not a supported name of a constant in ClassRegistryElementType");
    }
}
