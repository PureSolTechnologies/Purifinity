package com.puresol.gui.coding.analysis;

import java.util.Hashtable;

import javax.swingx.Mediator;
import javax.swingx.ToggleButton;
import javax.swingx.ToolBar;
import javax.swingx.Widget;
import javax.swingx.connect.Signal;
import javax.swingx.connect.Slot;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.AvailableMetrics;
import com.puresol.coding.analysis.CodeEvaluationSystem;

public class MetricSelectionToolBar extends ToolBar implements Mediator {

    private static final long serialVersionUID = -8325154422631866367L;

    private static final Logger logger =
	    Logger.getLogger(MetricSelectionToolBar.class);

    private final Hashtable<ToggleButton, AvailableMetrics> buttons =
	    new Hashtable<ToggleButton, AvailableMetrics>();

    public MetricSelectionToolBar() {
	super();
	initUI();
    }

    public MetricSelectionToolBar(String name, int direction) {
	super(name, direction);
	initUI();
    }

    private void initUI() {
	for (AvailableMetrics metric : CodeEvaluationSystem.getMetrics()) {
	    ToggleButton button = new ToggleButton(metric.getIdentifier());
	    button.setSelected(CodeEvaluationSystem.isMetricEvaluate(metric));
	    buttons.put(button, metric);
	    button.addMediator(this);
	    add(button);
	}
    }

    @Slot
    @Override
    public void widgetChanged(Widget widget) {
	ToggleButton button = (ToggleButton) widget;
	AvailableMetrics metric = buttons.get(button);
	CodeEvaluationSystem.setMetricEvaluate(metric, button.isSelected());
	logger.debug("Eval of " + metric.getIdentifier()
		+ " was switched to " + button.isSelected());
	refresh();
    }

    @Signal
    public void refresh() {
	connectionManager.emitSignal("refresh");
    }
}
