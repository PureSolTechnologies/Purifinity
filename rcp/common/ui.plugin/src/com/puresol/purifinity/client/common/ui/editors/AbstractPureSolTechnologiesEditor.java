package com.puresol.purifinity.client.common.ui.editors;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * This abstract class is used for PureSol Technologies' Editor components. This
 * class assures some common behavior for these editors.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractPureSolTechnologiesEditor extends EditorPart {

	private final AbstractUIPlugin activator;

	public AbstractPureSolTechnologiesEditor(AbstractUIPlugin activator) {
		super();
		this.activator = activator;
	}

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
