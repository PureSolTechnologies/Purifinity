package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;

import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

/**
 * This class contains a set of available repository types.
 * 
 * @author Rick-Rainer Ludwig
 */
public class RepositoryTypes implements Serializable {

	private static final long serialVersionUID = -3342924280689188983L;

	private final Set<RepositoryType> repositoryTypes = new LinkedHashSet<>();

	public RepositoryTypes(
			@JsonProperty("repositoryTypes") Set<RepositoryType> repositoryTypes) {
		this.repositoryTypes.addAll(repositoryTypes);
	}

	public Set<RepositoryType> getRepositoryTypes() {
		return repositoryTypes;
	}

}
