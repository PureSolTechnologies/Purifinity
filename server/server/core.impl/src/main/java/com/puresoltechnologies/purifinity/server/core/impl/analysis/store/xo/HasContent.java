package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition;

@EdgeDefinition("has_content")
@Retention(RetentionPolicy.RUNTIME)
public @interface HasContent {

}
