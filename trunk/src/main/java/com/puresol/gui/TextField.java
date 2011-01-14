/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.gui;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.puresol.gui.validator.Validator;

public class TextField extends JTextField implements CaretListener {

	private static final long serialVersionUID = -2719942064833804724L;

	private Validator validator = null;

	private String oldText;

	public TextField() {
		super();
		initialize();
	}

	public TextField(Validator validator) {
		super();
		initialize();
		setValidator(validator);
	}

	public TextField(String text) {
		super(text);
		initialize();
	}

	public TextField(String text, Validator validator) {
		super(text);
		initialize();
		setValidator(validator);
	}

	public TextField(String text, int length) {
		super(text, length);
		initialize();
	}

	public TextField(String text, int length, Validator validator) {
		super(text, length);
		initialize();
		setValidator(validator);
	}

	public TextField(int length) {
		super(length);
		initialize();
	}

	public TextField(int length, Validator validator) {
		super(length);
		initialize();
		setValidator(validator);
	}

	private void initialize() {
		addCaretListener(this);
		oldText = getText();
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	private void invokeValidator() {
		if (validator == null) {
			return;
		}
		if (validator.isValid(getText())) {
			setForeground(Color.BLACK);
		} else {
			setForeground(Color.RED);
		}
	}

	@Override
	public void caretUpdate(CaretEvent caretEvent) {
		String text = getText();
		if (!text.equals(oldText)) {
			oldText = text;
			invokeValidator();
		}
	}
}
