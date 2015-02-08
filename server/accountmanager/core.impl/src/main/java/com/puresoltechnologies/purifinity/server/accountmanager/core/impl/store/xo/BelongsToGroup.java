package com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition;

@EdgeDefinition(TitanElementNames.BELONGS_TO_GROUP_LABEL)
@Retention(RetentionPolicy.RUNTIME)
public @interface BelongsToGroup {

}
