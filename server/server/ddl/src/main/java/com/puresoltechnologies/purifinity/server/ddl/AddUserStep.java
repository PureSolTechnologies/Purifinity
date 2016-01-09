package com.puresoltechnologies.purifinity.server.ddl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
	    Set<String> types = new HashSet<>();
	    types.add("User");
	    Map<String, Object> properties = new HashMap<>();
	    properties.put(DuctileDBElementNames.USER_EMAIL_PROPERTY, userEmail.getAddress());
	    properties.put(DuctileDBElementNames.USER_NAME_PROPERTY, userName);
	    properties.put(DuctileDBElementNames.CREATION_TIME_PROPERTY, new Date());
	    DuctileDBVertex userVertex = ductileDBGraph.addVertex(types, properties);

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
	    userVertex.addEdge(DuctileDBElementNames.BELONGS_TO_GROUP_LABEL, roleVertex, new HashMap<>());

	    ductileDBGraph.commit();
	} catch (Exception e) {
	    ductileDBGraph.rollback();
	    throw new TransformationException("Could not create default user in Titan database.", e);
	}
    }
}
