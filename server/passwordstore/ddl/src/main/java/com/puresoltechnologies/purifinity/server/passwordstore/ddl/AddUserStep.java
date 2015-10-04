package com.puresoltechnologies.purifinity.server.passwordstore.ddl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.genesis.commons.TransformationException;
import com.puresoltechnologies.genesis.commons.TransformationMetadata;
import com.puresoltechnologies.genesis.transformation.phoenix.PhoenixTransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.TransformationStep;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordData;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordState;
import com.puresoltechnologies.purifinity.server.passwordstore.utils.encrypt.Encrypter1;

public class AddUserStep implements TransformationStep {

    private static final Logger logger = LoggerFactory.getLogger(AddUserStep.class);

    private final PhoenixTransformationSequence sequence;
    private final EmailAddress user;
    private final String password;
    private final PasswordState state;
    private final String developer;
    private final String comment;

    public AddUserStep(PhoenixTransformationSequence sequence, EmailAddress user, String password, PasswordState state,
	    String developer, String comment) {
	super();
	this.sequence = sequence;
	this.user = user;
	this.password = password;
	this.state = state;
	this.developer = developer;
	this.comment = comment;
    }

    @Override
    public void transform() throws TransformationException {
	logger.info("Add first administrator account.");
	Connection connection = sequence.getConnection();
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("UPSERT INTO " + PasswordStoreDatabaseTransformator.PASSWORD_TABLE_NAME//
			+ " (created, " //
			+ "last_modified, " //
			+ "email," //
			+ "password, " //
			+ "state, " + "activation_key) VALUES (?,?,?,?,?,?) ")) {
	    Date now = new Date();
	    PasswordData passwordData = new PasswordData(1, Encrypter1.encrypt(password));
	    preparedStatement.setTime(1, new Time(now.getTime()));
	    preparedStatement.setTime(2, new Time(now.getTime()));
	    preparedStatement.setString(3, user.getAddress());
	    preparedStatement.setString(4, passwordData.toString());
	    preparedStatement.setString(5, state.name());
	    preparedStatement.setString(6, "");
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Cannot rollback user creation for user '" + user + "'.", e1);
	    }
	    throw new TransformationException("Could not add user '" + user + "'.", e);
	}
    }

    @Override
    public TransformationMetadata getMetadata() {
	return new TransformationMetadata(sequence.getMetadata(), developer, "Create password for " + user.toString(),
		comment);
    }

}
