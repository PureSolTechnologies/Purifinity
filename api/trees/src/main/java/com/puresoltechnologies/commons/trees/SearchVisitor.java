package com.puresoltechnologies.commons.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * This {@link TreeVisitor} implementation is for searching a whole tree for a
 * special {@link TreeSearchCriterion}. This implementation looks through the
 * whole tree and returns a list of all node matching the criterion.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the {@link Tree} type to be used.
 */
public class SearchVisitor<T extends Tree<T>> implements TreeVisitor<T> {

	private final TreeSearchCriterion<T> criterion;

	private final List<T> searchResult = new ArrayList<T>();

	public SearchVisitor(TreeSearchCriterion<T> criterion) {
		super();
		this.criterion = criterion;
	}

	@Override
	public WalkingAction visit(T tree) {
		if (criterion.accepted(tree)) {
			searchResult.add(tree);
		}
		return WalkingAction.PROCEED;
	}

	public List<T> getSearchResult() {
		return searchResult;
	}
}
