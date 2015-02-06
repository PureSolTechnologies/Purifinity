package com.puresoltechnologies.purifinity.server.passwordstore.rest.impl;

import javax.inject.Inject;
import javax.ws.rs.NotAcceptableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.IllegalEmailAddressException;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.passwordstore.core.api.PasswordStore;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.api.PasswordActivationEntity;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.api.PasswordAuthenticationEntity;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.api.PasswordChangeEntity;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.api.PasswordCreationEntity;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.api.PasswordStoreRestInterface;

public class PasswordStoreRestService implements PasswordStoreRestInterface {

    private static final Logger logger = LoggerFactory
	    .getLogger(PasswordStoreRestService.class);

    @Inject
    private PasswordStore passwordStore;

    @Override
    public String createPassword(PasswordCreationEntity entity)
	    throws PasswordCreationException {
	try {
	    EmailAddress emailAddress = new EmailAddress(entity.getEmail());
	    Password password = new Password(entity.getPassword());
	    return passwordStore.createPassword(emailAddress, password);
	} catch (IllegalEmailAddressException e) {
	    logger.error("Could not create password.", e);
	    throw new NotAcceptableException("Invalid email address '"
		    + entity.getEmail() + "'.");
	}
    }

    @Override
    public EmailAddress activatePassword(PasswordActivationEntity entity)
	    throws PasswordActivationException {
	return passwordStore.activatePassword(entity.getEmail(),
		entity.getActivationKey());
    }

    @Override
    public boolean authenticate(PasswordAuthenticationEntity entity) {
	return passwordStore.authenticate(new EmailAddress(entity.getEmail()),
		new Password(entity.getPassword()));
    }

    @Override
    public boolean changePassword(PasswordChangeEntity entity)
	    throws PasswordChangeException {
	return passwordStore.changePassword(entity.getEmail(),
		entity.getOldPassword(), entity.getNewPassword());
    }

    @Override
    public Password resetPassword(String emailAddress)
	    throws PasswordResetException {
	return passwordStore.resetPassword(new EmailAddress(emailAddress));
    }

    @Override
    public void deletePassword(String emailAddress) {
	passwordStore.removePassword(new EmailAddress(emailAddress));
    }
}
