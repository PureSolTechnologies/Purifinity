package com.puresoltechnologies.purifinity.server.ddl;

import java.util.Date;
import java.util.Iterator;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.genesis.commons.TransformationException;
import com.puresoltechnologies.genesis.transformation.titan.AbstractTitanTransformationStep;
import com.puresoltechnologies.genesis.transformation.titan.TitanTransformationSequence;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.SupportedRoles;
import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanVertex;
import com.tinkerpop.blueprints.Vertex;

public class AddUserStep extends AbstractTitanTransformationStep {

    private final EmailAddress userEmail;
    private final String userName;
    private final SupportedRoles role;

    public AddUserStep(TitanTransformationSequence sequence,
	    EmailAddress userEmail, String userName, SupportedRoles role,
	    String developer, String comment) {
	super(sequence, developer, "Add user " + userEmail.getAddress(),
		comment);
	this.userEmail = userEmail;
	this.userName = userName;
	this.role = role;
    }

    @Override
    public void transform() throws TransformationException {
	TitanGraph titanGraph = getTitanGraph();
	titanGraph.buildTransaction();
	try {
	    TitanVertex userVertex = titanGraph.addVertex();
	    userVertex.setProperty("_xo_discriminator_user", "user");
	    userVertex.setProperty(TitanElementNames.USER_EMAIL_PROPERTY,
		    userEmail.getAddress());
	    userVertex.setProperty(TitanElementNames.USER_NAME_PROPERTY,
		    userName);
	    userVertex.setProperty(TitanElementNames.CREATION_TIME_PROPERTY,
		    new Date());

	    @SuppressWarnings("unchecked")
	    Iterable<Vertex> roles = titanGraph.query()
		    .has(TitanElementNames.ROLE_ID_PROPERTY, role.getId())
		    .vertices();
	    Iterator<Vertex> roleIterator = roles.iterator();
	    if (!roleIterator.hasNext()) {
		throw new TransformationException("No role vertex for "
			+ role.getName() + " was found.");
	    }
	    Vertex roleVertex = roleIterator.next();
	    if (roleIterator.hasNext()) {
		throw new TransformationException("Multiple role vertices for "
			+ role.getName() + " were found.");
	    }
	    userVertex.addEdge(TitanElementNames.BELONGS_TO_GROUP_LABEL, roleVertex);

	    titanGraph.commit();
	} catch (Exception e) {
	    titanGraph.rollback();
	    throw new TransformationException(
		    "Could not create default user in Titan database.", e);
	}
    }
}
