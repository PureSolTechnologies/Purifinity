package com.puresol.gui.coding.analysis;

import java.util.Hashtable;

import javax.swingx.Mediator;
import javax.swingx.ToggleButton;
import javax.swingx.ToolBar;
import javax.swingx.Widget;
import javax.swingx.connect.Signal;
import javax.swingx.connect.Slot;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.EvaluatorManager;
import com.puresol.coding.analysis.metrics.Metric;

public class MetricSelectionToolBar extends ToolBar implements Mediator {

    private static final long serialVersionUID = -8325154422631866367L;

    private static final Logger logger = Logger
	    .getLogger(MetricSelectionToolBar.class);

    private final Hashtable<ToggleButton, Class<? extends Metric>> buttons = new Hashtable<ToggleButton, Class<? extends Metric>>();

    public MetricSelectionToolBar() {
	super();
	initUI();
    }

    public MetricSelectionToolBar(String name, int direction) {
	super(name, direction);
	initUI();
    }

    private void initUI() {
	for (Class<? extends Metric> metric : EvaluatorManager
		.getMetricClasses()) {
	    ToggleButton button = new ToggleButton(metric.getSimpleName());
	    button.setSelected(EvaluatorManager.isMetricEvaluate(metric));
	    buttons.put(button, metric);
	    button.addMediator(this);
	    add(button);
	}
    }

    @Slot
    @Override
    public void widgetChanged(Widget widget) {
	ToggleButton button = (ToggleButton) widget;
	Class<? extends Metric> metric = buttons.get(button);
	EvaluatorManager.setMetricEvaluate(metric, button.isSelected());
	logger.debug("Eval of " + metric.getName() + " was switched to "
		+ button.isSelected());
	refresh();
    }

    @Signal
    public void refresh() {
	connectionManager.emitSignal("refresh");
    }
}
