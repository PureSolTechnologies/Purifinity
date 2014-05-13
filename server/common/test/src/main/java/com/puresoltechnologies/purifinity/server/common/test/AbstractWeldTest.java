package com.puresoltechnologies.purifinity.server.common.test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * This abstract class if for testing with Weld only. This class starts Weld and
 * provides some convinient methods to handle it.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractWeldTest {

	private static Weld weld = null;
	private static WeldContainer weldContainer = null;

	@BeforeClass
	public final static void initializeWeld() {
		weld = new Weld();
		weldContainer = weld.initialize();
	}

	@AfterClass
	public final static void destroyWeld() {
		weld.shutdown();
	}

	@SafeVarargs
	public final <C> C getInstance(Class<C> clazz,
			Class<? extends Annotation>... annotations) {
		List<Annotation> annotationList = new ArrayList<>();
		for (final Class<? extends Annotation> annotation : annotations) {
			annotationList.add(new Annotation() {

				@Override
				public Class<? extends Annotation> annotationType() {
					return annotation;
				}
			});
		}
		return getInstance(clazz,
				annotationList.toArray(new Annotation[annotationList.size()]));
	}

	public final <C> C getInstance(Class<C> clazz) {
		return weldContainer.instance().select(clazz).get();
	}

	public final <C> C getInstance(Class<C> clazz, Annotation... annotations) {
		return weldContainer.instance().select(clazz, annotations).get();
	}
}
