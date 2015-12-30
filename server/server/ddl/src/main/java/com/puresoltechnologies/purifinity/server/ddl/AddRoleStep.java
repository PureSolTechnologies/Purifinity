package com.puresoltechnologies.purifinity.server.ddl;

import java.io.IOException;
import java.util.Date;

import com.puresoltechnologies.ductiledb.api.DuctileDBGraph;
import com.puresoltechnologies.ductiledb.api.DuctileDBVertex;
import com.puresoltechnologies.genesis.commons.TransformationException;
import com.puresoltechnologies.genesis.transformation.ductiledb.AbstractDuctileDBTransformationStep;
import com.puresoltechnologies.genesis.transformation.ductiledb.DuctileDBTransformationSequence;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.SupportedRoles;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileDBElementNames;

public class AddRoleStep extends AbstractDuctileDBTransformationStep {

    private final SupportedRoles role;

    public AddRoleStep(DuctileDBTransformationSequence sequence, SupportedRoles role, String developer,
	    String comment) {
	super(sequence, developer, "Add role " + role.getName(), comment);
	this.role = role;
    }

    @Override
    public void transform() throws TransformationException {
	DuctileDBGraph titanGraph = getDuctileDBGraph();
	try {
	    DuctileDBVertex administratorRoleVertex = titanGraph.addVertex();
	    administratorRoleVertex.setProperty("_xo_discriminator_role", "role");
	    administratorRoleVertex.setProperty(DuctileDBElementNames.ROLE_ID_PROPERTY, role.getId());
	    administratorRoleVertex.setProperty(DuctileDBElementNames.ROLE_NAME_PROPERTY, role.getName());
	    administratorRoleVertex.setProperty(DuctileDBElementNames.CREATION_TIME_PROPERTY, new Date());
	    titanGraph.commit();
	} catch (Exception e) {
	    try {
		titanGraph.rollback();
	    } catch (IOException e1) {
		throw new TransformationException("Could not rollback DuctileDB.", e1);
	    }
	    throw new TransformationException("Could not create default roles in Titan database.", e);
	}
    }

}
