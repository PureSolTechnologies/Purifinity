package com.puresol.coding.client.content;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Combo;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresol.coding.client.Activator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;

public class ProjectEvaluatorFactoryComboViewer extends ComboViewer {

    public ProjectEvaluatorFactoryComboViewer(Combo list) {
	super(list);
	setContentProvider(new ProjectEvaluatorFactoryContentProvider());
	setLabelProvider(new LabelProvider() {
	    @Override
	    public String getText(Object element) {
		ProjectEvaluatorFactory evaluator = (ProjectEvaluatorFactory) element;
		return evaluator.getName();
	    }
	});
	setInput(getListOfProjectEvaluators());
    }

    private List<ProjectEvaluatorFactory> getListOfProjectEvaluators() {
	try {
	    BundleContext bundleContext = Activator.getDefault().getBundle()
		    .getBundleContext();
	    Collection<ServiceReference<ProjectEvaluatorFactory>> serviceReferences = bundleContext
		    .getServiceReferences(ProjectEvaluatorFactory.class, null);
	    List<ProjectEvaluatorFactory> evaluatorFactories = new ArrayList<ProjectEvaluatorFactory>();
	    for (ServiceReference<ProjectEvaluatorFactory> serviceReference : serviceReferences) {
		evaluatorFactories.add(bundleContext
			.getService(serviceReference));
	    }
	    return evaluatorFactories;
	} catch (InvalidSyntaxException e) {
	    throw new RuntimeException(
		    "Could not retrieve the project evaluators!", e);
	}
    }

}
