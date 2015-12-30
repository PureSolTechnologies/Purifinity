package com.puresoltechnologies.purifinity.server.ddl;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.ductiledb.api.DuctileDBGraph;
import com.puresoltechnologies.ductiledb.api.DuctileDBVertex;
import com.puresoltechnologies.genesis.commons.TransformationException;
import com.puresoltechnologies.genesis.transformation.ductiledb.AbstractDuctileDBTransformationStep;
import com.puresoltechnologies.genesis.transformation.ductiledb.DuctileDBTransformationSequence;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.SupportedRoles;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileDBElementNames;

public class AddUserStep extends AbstractDuctileDBTransformationStep {

    private final EmailAddress userEmail;
    private final String userName;
    private final SupportedRoles role;

    public AddUserStep(DuctileDBTransformationSequence sequence, EmailAddress userEmail, String userName,
	    SupportedRoles role, String developer, String comment) {
	super(sequence, developer, "Add user " + userEmail.getAddress(), comment);
	this.userEmail = userEmail;
	this.userName = userName;
	this.role = role;
    }

    @Override
    public void transform() throws TransformationException {
	DuctileDBGraph ductileDBGraph = getDuctileDBGraph();
	try {
	    DuctileDBVertex userVertex = ductileDBGraph.addVertex();
	    userVertex.setProperty("_xo_discriminator_user", "user");
	    userVertex.setProperty(DuctileDBElementNames.USER_EMAIL_PROPERTY, userEmail.getAddress());
	    userVertex.setProperty(DuctileDBElementNames.USER_NAME_PROPERTY, userName);
	    userVertex.setProperty(DuctileDBElementNames.CREATION_TIME_PROPERTY, new Date());

	    Iterable<DuctileDBVertex> roles = ductileDBGraph.getVertices(DuctileDBElementNames.ROLE_ID_PROPERTY,
		    role.getId());
	    Iterator<DuctileDBVertex> roleIterator = roles.iterator();
	    if (!roleIterator.hasNext()) {
		throw new TransformationException("No role vertex for " + role.getName() + " was found.");
	    }
	    DuctileDBVertex roleVertex = roleIterator.next();
	    if (roleIterator.hasNext()) {
		throw new TransformationException("Multiple role vertices for " + role.getName() + " were found.");
	    }
	    userVertex.addEdge(DuctileDBElementNames.BELONGS_TO_GROUP_LABEL, roleVertex);

	    ductileDBGraph.commit();
	} catch (Exception e) {
	    try {
		ductileDBGraph.rollback();
	    } catch (IOException e1) {
		throw new TransformationException("Could not rollback DuctileDB.", e1);
	    }
	    throw new TransformationException("Could not create default user in Titan database.", e);
	}
    }
}
