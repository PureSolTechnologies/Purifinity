package com.puresoltechnologies.purifinity.evaluation.domain.style;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.misc.hash.HashId;

public class GenericRunStyleIssues {

    private final Set<StyleParameter> parameters = new HashSet<>();
    private final Map<HashId, GenericFileStyleIssues> fileMetrics = new HashMap<>();

}
