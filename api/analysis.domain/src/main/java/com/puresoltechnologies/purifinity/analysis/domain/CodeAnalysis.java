/***************************************************************************
 *
 *   Analyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.analysis.domain;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.puresoltechnologies.commons.misc.TimeAwareness;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;

/**
 * This is a interface to a single analysis. It's used to implement a language
 * independent way to access a single file analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class CodeAnalysis implements Serializable, TimeAwareness {

	private static final long serialVersionUID = 4670045857614462051L;

	private final Date time;
	private final long duration;
	private final AnalysisInformation analysisInformation;
	private final List<CodeRange> analyzableCodeRanges;
	private final UniversalSyntaxTree universalSyntaxTree;

	public CodeAnalysis(Date time, long duration, String languageName,
			String languageVersion, AnalysisInformation analysisInformation,
			List<CodeRange> analyzableCodeRanges,
			UniversalSyntaxTree universalSyntaxTree) {
		super();
		this.time = time;
		this.duration = duration;
		this.analysisInformation = analysisInformation;
		this.analyzableCodeRanges = analyzableCodeRanges;
		this.universalSyntaxTree = universalSyntaxTree;
	}

	/**
	 * This method returns the time stamp of the analysis. This can be used for
	 * validity analysis by time stamp comparison for evaluators.
	 * 
	 * @return
	 * @throws IOException
	 */
	@Override
	public Date getStartTime() {
		return time;
	}

	/**
	 * This method returns the time effort which was needed for analysis.
	 * 
	 * @return Returns the time effort in milliseconds
	 * @throws IOException
	 */
	@Override
	public long getDuration() {
		return duration;
	}

	/**
	 * The file which was analyzed is returned.
	 * 
	 * @return The analyzed file is returned.
	 */
	public AnalysisInformation getAnalysisInformation() {
		return analysisInformation;
	}

	/**
	 * Returns a list of code ranges which can be analyzed and evaluated
	 * separately.
	 * 
	 * @return A {@link List} of {@link CodeRange} is returned.
	 */
	public List<CodeRange> getAnalyzableCodeRanges() {
		return analyzableCodeRanges;
	}

	/**
	 * Returns the universal sytax tree.
	 * 
	 * @return A {@link UniversalSyntaxTree} object is returned.
	 */
	public UniversalSyntaxTree getUniversalSyntaxTree() {
		return universalSyntaxTree;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((analyzableCodeRanges == null) ? 0 : analyzableCodeRanges
						.hashCode());
		result = prime * result
				+ ((analysisInformation == null) ? 0 : analysisInformation.hashCode());
		result = prime * result + (int) (duration ^ (duration >>> 32));
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime
				* result
				+ ((universalSyntaxTree == null) ? 0 : universalSyntaxTree
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CodeAnalysis other = (CodeAnalysis) obj;
		if (analyzableCodeRanges == null) {
			if (other.analyzableCodeRanges != null)
				return false;
		} else if (!analyzableCodeRanges.equals(other.analyzableCodeRanges))
			return false;
		if (analysisInformation == null) {
			if (other.analysisInformation != null)
				return false;
		} else if (!analysisInformation.equals(other.analysisInformation))
			return false;
		if (duration != other.duration)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (universalSyntaxTree == null) {
			if (other.universalSyntaxTree != null)
				return false;
		} else if (!universalSyntaxTree.equals(other.universalSyntaxTree))
			return false;
		return true;
	}

}
