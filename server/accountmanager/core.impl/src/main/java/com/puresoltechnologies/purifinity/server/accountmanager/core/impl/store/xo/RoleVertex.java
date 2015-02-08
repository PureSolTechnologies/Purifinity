package com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo;

import java.util.Date;
import java.util.List;

import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition.Incoming;
import com.puresoltechnologies.xo.titan.api.annotation.Indexed;
import com.puresoltechnologies.xo.titan.api.annotation.Property;
import com.puresoltechnologies.xo.titan.api.annotation.VertexDefinition;

/**
 * This is the XO definition of a analysis project vertex in graph database.
 * 
 * @author Rick-Rainer Ludwig
 */
@VertexDefinition(RoleVertex.NAME)
public interface RoleVertex {

    public static final String NAME = "role";

    @Indexed(unique = true)
    @Property(TitanElementNames.ROLE_ID_PROPERTY)
    public String getId();

    public void setId(String id);

    @Property(TitanElementNames.ROLE_NAME_PROPERTY)
    public String getName();

    public void setName(String name);

    @Property(TitanElementNames.CREATION_TIME_PROPERTY)
    public Date getCreationTime();

    public void setCreationTime(Date time);

    @Incoming
    @BelongsToGroup
    public List<UserVertex> getUsers();

}
