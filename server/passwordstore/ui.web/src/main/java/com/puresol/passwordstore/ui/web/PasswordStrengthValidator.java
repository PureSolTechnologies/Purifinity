package com.puresol.passwordstore.ui.web;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.puresol.passwordstore.domain.PasswordStrengthCalculator;

@FacesValidator(value = "passwordStrengthValidator")
public class PasswordStrengthValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		HtmlInputText htmlInputText = (HtmlInputText) component;
		String password = (String) component.getAttributes().get("password");
		String confirm = (String) value;

		if (!password.equals(confirm)) {
			throw new ValidatorException(new FacesMessage(
					"Passwords are not equal."));
		}

		PasswordStrengthCalculator check = new PasswordStrengthCalculator(
				password);
		boolean strength = check.isValid();
		if (!strength) {
			throw new ValidatorException(new FacesMessage(
					htmlInputText.getLabel()
							+ ": Password is not strong enough!\nlog:"
							+ check.getLog().replaceAll("\\n", "<br/>")));
		}
	}
}