package com.puresol.gui;

import java.awt.BorderLayout;

import javax.i18n4java.Translator;
import javax.swingx.Dialog;

import com.puresol.utils.PropertyHandler;

public class PropertyDialog extends Dialog {

	private static final long serialVersionUID = 5830612066032999648L;

	private static final Translator translator = Translator
			.getTranslator(PropertyDialog.class);

	private PropertyEditor propertyEditor;

	public PropertyDialog(PropertyHandler propertyHandler) {
		super(translator.i18n("Property Editor"), true);
		initUI(propertyHandler);
	}

	private void initUI(PropertyHandler propertyHandler) {
		setLayout(new BorderLayout());

		add(propertyEditor = new PropertyEditor(propertyHandler),
				BorderLayout.CENTER);

		add(createDefaultOkCancelPanel(), BorderLayout.SOUTH);
		pack();
	}

	public PropertyHandler getPropertyHandler() {
		return propertyEditor.getPropertyHandler();
	}
}
