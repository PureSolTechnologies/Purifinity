package com.puresoltechnologies.purifinity.server.ddl;

import java.util.Date;

import com.puresoltechnologies.genesis.commons.TransformationException;
import com.puresoltechnologies.genesis.transformation.titan.AbstractTitanTransformationStep;
import com.puresoltechnologies.genesis.transformation.titan.TitanTransformationSequence;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.SupportedRoles;
import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanVertex;

public class AddRoleStep extends AbstractTitanTransformationStep {

    private final SupportedRoles role;

    public AddRoleStep(TitanTransformationSequence sequence,
	    SupportedRoles role, String developer, String comment) {
	super(sequence, developer, "Add role " + role.getName(), comment);
	this.role = role;
    }

    @Override
    public void transform() throws TransformationException {
	TitanGraph titanGraph = getTitanGraph();
	titanGraph.buildTransaction();
	try {
	    TitanVertex administratorRoleVertex = titanGraph.addVertex();
	    administratorRoleVertex.setProperty("_xo_discriminator_role",
		    "role");
	    administratorRoleVertex.setProperty(
		    TitanElementNames.ROLE_ID_PROPERTY, role.getId());
	    administratorRoleVertex.setProperty(
		    TitanElementNames.ROLE_NAME_PROPERTY, role.getName());
	    administratorRoleVertex.setProperty(
		    TitanElementNames.CREATION_TIME_PROPERTY, new Date());
	    titanGraph.commit();
	} catch (Exception e) {
	    titanGraph.rollback();
	    throw new TransformationException(
		    "Could not create default roles in Titan database.", e);
	}
    }

}