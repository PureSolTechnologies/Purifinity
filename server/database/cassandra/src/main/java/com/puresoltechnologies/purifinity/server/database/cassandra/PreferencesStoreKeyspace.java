package com.puresoltechnologies.purifinity.server.database.cassandra;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Qualifier
@Target({ TYPE, METHOD, FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PreferencesStoreKeyspace {

	public static final String NAME = "preferences_store";

}
