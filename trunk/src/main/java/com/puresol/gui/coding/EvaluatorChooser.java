package com.puresol.gui.coding;

import java.util.Hashtable;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import com.puresol.coding.evaluator.EvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluatorManager;
import com.puresol.gui.FreeList;
import com.puresol.osgi.OSGi;
import com.puresol.osgi.OSGiFrameworkListener;
import com.puresol.osgi.OSGiFrameworkManager;

public class EvaluatorChooser extends FreeList implements BundleListener,
		OSGiFrameworkListener {

	private static final long serialVersionUID = 8684347482453852261L;

	private final OSGi osgi = OSGiFrameworkManager.getInstance();

	public EvaluatorChooser() {
		super();
		osgi.addOSGiFrameworkListener(this);
		if (osgi.getContext() != null) {
			osgi.getContext().addBundleListener(this);
		}
		BundleContext context = osgi.getContext();
		if (context != null) {
			context.addServiceListener(new ServiceListener() {
				@Override
				public void serviceChanged(ServiceEvent event) {
					addEvaluators();
				}
			});
		}
		addEvaluators();
	}

	private synchronized void addEvaluators() {
		removeAll();
		List<ProjectEvaluatorFactory> evaluatorFactories = ProjectEvaluatorManager
				.getAll();
		Hashtable<Object, Object> values = new Hashtable<Object, Object>();
		for (EvaluatorFactory evaluatorFactory : evaluatorFactories) {
			values.put(evaluatorFactory.getName(), evaluatorFactory);
		}
		setListData(values);
	}

	@Override
	public void bundleChanged(BundleEvent arg0) {
		addEvaluators();
	}

	@Override
	public void preStart() {
	}

	@Override
	public void postStart() {
		if (osgi.getContext() != null) {
			osgi.getContext().addBundleListener(this);
		}
		addEvaluators();
	}

	@Override
	public void preStop() {
		if (osgi.getContext() != null) {
			osgi.getContext().removeBundleListener(this);
		}
		removeAll();
	}

	@Override
	public void postStop() {
	}

}
