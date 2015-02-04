package com.puresoltechnologies.purifinity.server.ddl;

import java.util.Date;
import java.util.Iterator;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.genesis.commons.TransformationException;
import com.puresoltechnologies.genesis.transformation.titan.AbstractTitanTransformationStep;
import com.puresoltechnologies.genesis.transformation.titan.TitanTransformationSequence;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.SupportedRoles;
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
	    userVertex.setProperty("user_email", userEmail.getAddress());
	    userVertex.setProperty("user_name", userName);
	    userVertex.setProperty("time.creation", new Date());

	    @SuppressWarnings("unchecked")
	    Iterable<Vertex> roles = titanGraph.query()
		    .has("role_id", role.getId()).vertices();
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
	    userVertex.addEdge("belongsTo", roleVertex);

	    titanGraph.commit();
	} catch (Exception e) {
	    titanGraph.rollback();
	    throw new TransformationException(
		    "Could not create default user in Titan database.", e);
	}
    }
}
