package com.puresoltechnologies.purifinity.server.ddl;

import static com.puresoltechnologies.ductiledb.core.schema.HBaseSchema.DUCTILEDB_NAMESPACE;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.ServiceException;
import com.puresoltechnologies.ductiledb.api.ElementType;
import com.puresoltechnologies.ductiledb.api.schema.UniqueConstraint;
import com.puresoltechnologies.ductiledb.core.DuctileDBGraphFactory;
import com.puresoltechnologies.genesis.commons.ProvidedVersionRange;
import com.puresoltechnologies.genesis.commons.SequenceMetadata;
import com.puresoltechnologies.genesis.commons.TransformationException;
import com.puresoltechnologies.genesis.transformation.ductiledb.AbstractDuctileDBTransformationStep;
import com.puresoltechnologies.genesis.transformation.ductiledb.DuctileDBDefinePropertyStep;
import com.puresoltechnologies.genesis.transformation.ductiledb.DuctileDBDefineTypeStep;
import com.puresoltechnologies.genesis.transformation.ductiledb.DuctileDBTransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.ComponentTransformator;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileDBElementNames;
import com.puresoltechnologies.versioning.Version;

public class DuctileDBDatabaseTransformator implements ComponentTransformator {

    private static final Logger logger = LoggerFactory.getLogger(DuctileDBDatabaseTransformator.class);

    public static final String DUCTILE_DB_HOST = "localhost";
    public static final String COMPONENT_NAME = "DuctileDBGraphDatabase";
    public static final Set<String> DEPENDENCIES = new HashSet<>();

    static {
	DEPENDENCIES.add(HadoopTransformator.COMPONENT_NAME);
    }

    @Override
    public String getComponentName() {
	return COMPONENT_NAME;
    }

    @Override
    public Set<String> getDependencies() {
	return DEPENDENCIES;
    }

    @Override
    public boolean isHostBased() {
	return false;
    }

    @Override
    public Set<TransformationSequence> getSequences() {
	Set<TransformationSequence> sequences = new HashSet<>();
	sequences.add(migrateVersion0_4_0());
	return sequences;
    }

    private TransformationSequence migrateVersion0_4_0() {
	Version startVersion = new Version(0, 0, 0);
	Version targetVersion = new Version(0, 4, 0);
	ProvidedVersionRange versionRange = new ProvidedVersionRange(targetVersion, null);
	SequenceMetadata metadata = new SequenceMetadata(getComponentName(), startVersion, versionRange);
	DuctileDBTransformationSequence sequence = new DuctileDBTransformationSequence(DUCTILE_DB_HOST, metadata);

	sequence.appendTransformation(new AbstractDuctileDBTransformationStep(sequence, "Rick-Rainer Ludwig",
		"Create DuctileDB Keyspace", "Create DuctileDB Keyspace") {
	    @Override
	    public void transform() throws TransformationException {
		// intentionally left blank
	    }
	});

	sequence.appendTransformation(new DuctileDBDefinePropertyStep<>(sequence, "Rick-Rainer Ludwig",
		"Create property for creation time.", ElementType.VERTEX, DuctileDBElementNames.CREATION_TIME_PROPERTY,
		Date.class, UniqueConstraint.NONE));
	sequence.appendTransformation(new DuctileDBDefinePropertyStep<>(sequence, "Rick-Rainer Ludwig",
		"Create property for role id.", ElementType.VERTEX, DuctileDBElementNames.ROLE_ID_PROPERTY,
		String.class, UniqueConstraint.TYPE));
	sequence.appendTransformation(new DuctileDBDefinePropertyStep<>(sequence, "Rick-Rainer Ludwig",
		"Create property for role name.", ElementType.VERTEX, DuctileDBElementNames.ROLE_NAME_PROPERTY,
		String.class, UniqueConstraint.TYPE));

	sequence.appendTransformation(
		new DuctileDBDefineTypeStep(sequence, "Rick-Rainer Ludwig", "Create type for user management role.",
			ElementType.VERTEX, "Role", DuctileDBElementNames.ROLE_ID_PROPERTY,
			DuctileDBElementNames.ROLE_NAME_PROPERTY, DuctileDBElementNames.CREATION_TIME_PROPERTY));

	sequence.appendTransformation(new DuctileDBDefinePropertyStep<>(sequence, "Rick-Rainer Ludwig",
		"Create property for user email.", ElementType.VERTEX, DuctileDBElementNames.USER_EMAIL_PROPERTY,
		String.class, UniqueConstraint.TYPE));
	sequence.appendTransformation(new DuctileDBDefinePropertyStep<>(sequence, "Rick-Rainer Ludwig",
		"Create property for user name.", ElementType.VERTEX, DuctileDBElementNames.USER_NAME_PROPERTY,
		String.class, UniqueConstraint.NONE));

	sequence.appendTransformation(
		new DuctileDBDefineTypeStep(sequence, "Rick-Rainer Ludwig", "Create type for user management user.",
			ElementType.VERTEX, "User", DuctileDBElementNames.USER_EMAIL_PROPERTY,
			DuctileDBElementNames.USER_NAME_PROPERTY, DuctileDBElementNames.CREATION_TIME_PROPERTY));

	return sequence;
    }

    @Override
    public void dropAll() {
	try (Connection connection = DuctileDBGraphFactory.createConnection("localhost", 2181, "localhost", 60000)) {
	    removeTables(connection);
	} catch (IOException | ServiceException e) {
	    throw new RuntimeException("Could not drop DuctileDB.", e);
	}
    }

    private static void removeTables(Connection connection) throws IOException {
	logger.info("Remove all DuctileDB tables...");
	Admin admin = connection.getAdmin();
	HTableDescriptor[] listTables = admin.listTables();
	for (HTableDescriptor tableDescriptor : listTables) {
	    TableName tableName = tableDescriptor.getTableName();
	    if (DUCTILEDB_NAMESPACE.equals(tableName.getNamespaceAsString())) {
		removeTable(admin, tableName);
	    }
	}
	NamespaceDescriptor[] namespaceDescriptors = admin.listNamespaceDescriptors();
	for (NamespaceDescriptor namespaceDescriptor : namespaceDescriptors) {
	    if (DUCTILEDB_NAMESPACE.equals(namespaceDescriptor.getName())) {
		admin.deleteNamespace(DUCTILEDB_NAMESPACE);
	    }
	}
	logger.info("All DuctileDB tables removed.");
    }

    private static void removeTable(Admin admin, TableName tableName) throws IOException {
	if (admin.isTableEnabled(tableName)) {
	    logger.info("Disable table '" + tableName + "'...");
	    admin.disableTable(tableName);
	    logger.info("Table '" + tableName + "' disabled.");
	}
	logger.info("Delete table '" + tableName + "'...");
	admin.deleteTable(tableName);
	logger.info("Table '" + tableName + "' deleted.");
    }

}
