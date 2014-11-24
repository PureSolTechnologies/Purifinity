package com.puresoltechnologies.purifinity.server.passwordstore.rest.impl;

import javax.inject.Inject;

import com.puresoltechnologies.commons.types.EmailAddress;
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

    @Inject
    private PasswordStore passwordStore;

    @Override
    public String createPassword(PasswordCreationEntity entity)
	    throws PasswordCreationException {
	return passwordStore.createPassword(entity.getEmail(),
		entity.getPassword());
    }

    @Override
    public EmailAddress activatePassword(PasswordActivationEntity entity)
	    throws PasswordActivationException {
	return passwordStore.activatePassword(entity.getEmail(),
		entity.getActivationKey());
    }

    @Override
    public boolean authenticate(PasswordAuthenticationEntity entity) {
	return passwordStore.authenticate(entity.getEmail(),
		entity.getPassword());
    }

    @Override
    public boolean changePassword(PasswordChangeEntity entity)
	    throws PasswordChangeException {
	return passwordStore.changePassword(entity.getEmail(),
		entity.getOldPassword(), entity.getNewPassword());
    }

    @Override
    public Password resetPassword(EmailAddress emailAddress)
	    throws PasswordResetException {
	return passwordStore.resetPassword(emailAddress);
    }
}
