package com.puresol.entities.forms;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TemplateElementLayout {
	public String name();

	public int id();

	public boolean optional(); // NULLABLE

	public boolean hidden() default false;
}
