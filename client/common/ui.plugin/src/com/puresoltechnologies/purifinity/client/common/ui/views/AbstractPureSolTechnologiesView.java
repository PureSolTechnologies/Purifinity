package com.puresoltechnologies.purifinity.client.common.ui.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * This abstract class is used for PureSol Technologies' View components. This
 * class assures some common behavior for these views.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractPureSolTechnologiesView extends ViewPart {

	private final AbstractUIPlugin activator;

	public AbstractPureSolTechnologiesView(AbstractUIPlugin activator) {
		super();
		this.activator = activator;
	}

	/**
	 * This method was implemented to add the help system texts.
	 */
	@Override
	public void createPartControl(Composite parent) {
		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(
						parent,
						activator.getBundle().getSymbolicName() + "."
								+ this.getClass().getSimpleName());

	}
}
