/***************************************************************************
 *
 *   PasswordTest.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.entities.forms;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class PasswordTest extends TestCase {

	@Test
	public void testAnnotations() {
		Retention retention = Password.class.getAnnotation(Retention.class);
		Assert.assertEquals(RetentionPolicy.RUNTIME, retention.value());
		Target target = Password.class.getAnnotation(Target.class);
		Assert.assertEquals(1, target.value().length);
		Assert.assertEquals(ElementType.METHOD, target.value()[0]);
	}
}
