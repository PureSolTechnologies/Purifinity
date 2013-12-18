package com.puresoltechnologies.purifinity.jboss.test.mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.ProcessInjectionTarget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a special CDI extension to get Mockito to mock CDI injected objects.
 * 
 * @author Rick-Rainer Ludwig
 */
public class MockitoExtension implements Extension {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MockitoExtension.class);

	/**
	 * Contains all types annotated with {@link Mock} and the corresponding
	 * instance.
	 */
	private final Map<Class<?>, Object> mockTypes = new HashMap<Class<?>, Object>();

	/**
	 * Constructor.
	 */
	public MockitoExtension() {
		LOGGER.info("Initializing " + MockitoExtension.class.getName());
	}

	/**
	 * Resets all mock instances.
	 */
	public void resetMocks() {
		LOGGER.info("Resetting mocks.");
		for (Object mock : mockTypes.values()) {
			reset(mock);
		}
	}

	/**
	 * Observes all types at container startup and registers all types annotated
	 * with {@link Mock}.
	 * 
	 * @param processAnnotatedType
	 *            The {@link ProcessAnnotatedType}.
	 * 
	 * @param <X>
	 *            The type.
	 */
	protected <X> void processAnnotatedType(
			@Observes ProcessAnnotatedType<X> processAnnotatedType) {
		AnnotatedType<X> annotatedType = processAnnotatedType
				.getAnnotatedType();
		Class<X> type = annotatedType.getJavaClass();
		if (type.isAnnotationPresent(Mock.class)) {
			LOGGER.info("Registering mock for " + type.getName());
			mockTypes.put(type, mock(type));
			// the mock type is not directly visible via CDI, it is
			// transparently inject instead of the replaced type.
			processAnnotatedType.veto();
		}
	}

	/**
	 * Processes all detected injection targets (i.e. classes or methods which
	 * get other instances injected).
	 * <p>
	 * If an instance is produced for injection where a mock type exists in
	 * {@link #mockTypes} then it will be replaced by the corresponding Mockito
	 * instance.
	 * 
	 * @param <X>
	 *            The type.
	 * @param pit
	 *            The {@link ProcessInjectionTarget}.
	 */
	@SuppressWarnings("unchecked")
	<X> void processInjectionTarget(
			@Observes final ProcessInjectionTarget<X> pit) {
		final InjectionTarget<X> it = pit.getInjectionTarget();
		final Class<?> type = pit.getAnnotatedType().getJavaClass();
		for (Entry<Class<?>, Object> mockEntry : mockTypes.entrySet()) {
			if (type.isAssignableFrom(mockEntry.getKey())) {
				LOGGER.info("Preparing mock replacement for type "
						+ type.getName());
				final X mockInstance = (X) mockEntry.getValue();
				InjectionTarget<X> mockInjectionTarget = new InjectionTarget<X>() {

					@Override
					public X produce(CreationalContext<X> ctx) {
						LOGGER.info("Replacing instance with mock for type "
								+ type.getName());
						return mockInstance;
					}

					@Override
					public void dispose(X instance) {
						it.dispose(instance);
					}

					@Override
					public Set<InjectionPoint> getInjectionPoints() {
						return it.getInjectionPoints();
					}

					@Override
					public void inject(X instance, CreationalContext<X> ctx) {
						it.inject(instance, ctx);
					}

					@Override
					public void postConstruct(X instance) {
						it.postConstruct(instance);
					}

					@Override
					public void preDestroy(X instance) {
						it.preDestroy(instance);
					}
				};
				pit.setInjectionTarget(mockInjectionTarget);
			}
		}
	}
}
