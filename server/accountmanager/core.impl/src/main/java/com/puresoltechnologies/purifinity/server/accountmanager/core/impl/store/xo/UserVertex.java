package com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo;

import java.util.Date;

import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition.Outgoing;
import com.puresoltechnologies.xo.titan.api.annotation.Indexed;
import com.puresoltechnologies.xo.titan.api.annotation.Property;
import com.puresoltechnologies.xo.titan.api.annotation.VertexDefinition;

/**
 * This is the XO definition of a analysis project vertex in graph database.
 * 
 * @author Rick-Rainer Ludwig
 */
@VertexDefinition(UserVertex.NAME)
public interface UserVertex {

    public static final String NAME = "user";

    @Indexed(unique = true)
    @Property(TitanElementNames.USER_EMAIL_PROPERTY)
    public String getEmail();

    public void setEmail(String email);

    @Property(TitanElementNames.USER_NAME_PROPERTY)
    public String getName();

    public void setName(String name);

    @Property(TitanElementNames.CREATION_TIME_PROPERTY)
    public Date getCreationTime();

    public void setCreationTime(Date time);

    @Outgoing
    @BelongsToGroup
    public RoleVertex getRole();

    public void setRole(RoleVertex role);

}
