package com.puresoltechnologies.purifinity.server.common.rest.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation is used to define a rest method to be permitted for all users
 * anytime. This might be method like status, login and logout which need to be
 * available globally.
 * 
 * @author Rick-Rainer Ludwig
 */
@Documented
@Retention(RUNTIME)
@Target({ METHOD })
public @interface PermitAll {

}
