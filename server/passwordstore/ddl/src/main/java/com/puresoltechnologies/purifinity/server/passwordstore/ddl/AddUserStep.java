package com.puresoltechnologies.purifinity.server.passwordstore.ddl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.genesis.commons.TransformationException;
import com.puresoltechnologies.genesis.commons.TransformationMetadata;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraTransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.TransformationStep;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountState;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordData;
import com.puresoltechnologies.purifinity.server.passwordstore.utils.encrypt.Encrypter1;

public class AddUserStep implements TransformationStep {

    private static final Logger logger = LoggerFactory
	    .getLogger(AddUserStep.class);

    private final CassandraTransformationSequence sequence;
    private final EmailAddress user;
    private final String password;
    private final AccountState state;
    private final String developer;
    private final String comment;

    public AddUserStep(CassandraTransformationSequence sequence,
	    EmailAddress user, String password, AccountState state,
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
	Session session = sequence.getSession();
	PreparedStatement preparedStatement = session.prepare("INSERT INTO "
		+ PasswordStoreDatabaseTransformator.PASSWORD_TABLE_NAME//
		+ " (created, " //
		+ "last_modified, " //
		+ "email," //
		+ "password, " //
		+ "state, " + "activation_key) VALUES (?,?,?,?,?,?) ");
	Date now = new Date();
	PasswordData passwordData = new PasswordData(1,
		Encrypter1.encrypt(password));
	BoundStatement boundStatement = preparedStatement.bind(now, now,
		user.getAddress(), passwordData.toString(), state.name(), "");
	session.execute(boundStatement);
    }

    @Override
    public TransformationMetadata getMetadata() {
	return new TransformationMetadata(sequence.getMetadata(), developer,
		"Create password for " + user.toString(), comment);
    }

}
