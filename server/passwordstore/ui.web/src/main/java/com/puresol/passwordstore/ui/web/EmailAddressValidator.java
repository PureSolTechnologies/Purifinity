package com.puresol.passwordstore.ui.web;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * This validator is used to validate an email address for valid format. This
 * validator uses the InternetAddress class to check email addresses. The
 * InternetAddress checks for RFC822 compliance.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
@FacesValidator(value = "emailAddressValidator")
public class EmailAddressValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent,
			Object value) throws ValidatorException {
		String email = (String) value;

		if (!com.puresoltechnologies.purifinity.server.wildfly.utils.EmailAddressValidator
				.validate(email)) {
			HtmlInputText htmlInputText = (HtmlInputText) uiComponent;
			FacesMessage facesMessage = new FacesMessage(
					htmlInputText.getLabel() + ": email format is not valid");
			throw new ValidatorException(facesMessage);
		}
	}
}
