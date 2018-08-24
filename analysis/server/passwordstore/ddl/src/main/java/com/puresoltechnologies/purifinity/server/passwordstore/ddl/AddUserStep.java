package com.puresoltechnologies.purifinity.server.passwordstore.ddl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.ductiledb.core.tables.TableStore;
import com.puresoltechnologies.ductiledb.core.tables.dml.BoundStatement;
import com.puresoltechnologies.ductiledb.core.tables.dml.DataManipulationLanguage;
import com.puresoltechnologies.ductiledb.core.tables.dml.PreparedStatement;
import com.puresoltechnologies.genesis.commons.TransformationException;
import com.puresoltechnologies.genesis.commons.TransformationMetadata;
import com.puresoltechnologies.genesis.transformation.ductiledb.DuctileDBTransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.TransformationStep;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordData;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordState;
import com.puresoltechnologies.purifinity.server.passwordstore.utils.encrypt.Encrypter1;

public class AddUserStep implements TransformationStep {

    private static final Logger logger = LoggerFactory.getLogger(AddUserStep.class);

    private final DuctileDBTransformationSequence sequence;
    private final EmailAddress user;
    private final String password;
    private final PasswordState state;
    private final String developer;
    private final String comment;

    public AddUserStep(DuctileDBTransformationSequence sequence, EmailAddress user, String password,
	    PasswordState state, String developer, String comment) {
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
	TableStore tableStore = sequence.getTableStore();
	DataManipulationLanguage dm = tableStore.getDataManipulationLanguage();
	PreparedStatement preparedStatement = tableStore
		.prepare("INSERT INTO " + PasswordStoreDatabaseTransformator.PASSWORD_TABLE_NAME//
			+ " (created, " //
			+ "last_modified, " //
			+ "email," //
			+ "password, " //
			+ "state, " + "activation_key) VALUES (?,?,?,?,?,?) ");
	Date now = new Date();
	PasswordData passwordData = new PasswordData(1, Encrypter1.encrypt(password));
	BoundStatement boundStatement = preparedStatement.bind(now, now, user.getAddress(), passwordData.toString(),
		state.name(), "");
	tableStore.execute(boundStatement);
    }

    @Override
    public TransformationMetadata getMetadata() {
	return new TransformationMetadata(sequence.getMetadata(), developer, "Create password for " + user.toString(),
		comment);
    }

}
