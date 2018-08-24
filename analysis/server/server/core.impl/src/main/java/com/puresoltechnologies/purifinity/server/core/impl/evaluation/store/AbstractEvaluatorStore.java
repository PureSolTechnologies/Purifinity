package com.puresoltechnologies.purifinity.server.core.impl.evaluation.store;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.Classification;
import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.server.core.api.analysis.common.SourceCodeLocationCreator;
import com.puresoltechnologies.versioning.Version;

public abstract class AbstractEvaluatorStore {

    /**
     * This method is used to extract the source code location from database.
     * 
     * @param resultSet
     *            is the {@link ResultSet} to read the source code location
     *            from.
     * @return A new {@link SourceCodeLocation} object is returned containing
     *         the result.
     * @throws SQLException
     *             is thrown in case of SQL issues.
     */
    protected SourceCodeLocation extractSourceCodeLocation(ResultSet resultSet) throws SQLException {
	String locationString = resultSet.getString("source_code_location");
	if (locationString == null) {
	    return new UnspecifiedSourceCodeLocation();
	}
	Properties properties = PropertiesUtils.fromString(locationString);
	return SourceCodeLocationCreator.createFromSerialization(properties);
    }

    /**
     * This method is used to extract the hash id out of a single
     * {@link ResultSet}.
     * 
     * @param resultSet
     *            is the {@link ResultSet} to read the {@link HashId} from.
     * @return A {@link HashId} object is returned containing the
     *         {@link HashId}.
     * @throws SQLException
     *             is thrown in case of SQL issues.
     */
    protected HashId extractHashId(ResultSet resultSet) throws SQLException {
	return HashId.valueOf(resultSet.getString("HASHID"));
    }

    /**
     * This method is used to extract the code range type out of a single
     * {@link ResultSet}.
     * 
     * @param resultSet
     *            is the {@link ResultSet} to read the {@link CodeRangeType}
     *            from.
     * @return A {@link CodeRangeType} object is returned containing the
     *         {@link CodeRangeType}.
     * @throws SQLException
     *             is thrown in case of SQL issues.
     */
    protected CodeRangeType extractCodeRangeType(ResultSet resultSet) throws SQLException {
	return CodeRangeType.valueOf(resultSet.getString("CODE_RANGE_TYPE"));
    }

    /**
     * This method is used to extract the severity out of a single
     * {@link ResultSet}.
     * 
     * @param resultSet
     *            is the {@link ResultSet} to read the {@link Severity} from.
     * @return A {@link Severity} object is returned containing the
     *         {@link Severity}.
     * @throws SQLException
     *             is thrown in case of SQL issues.
     */
    protected Severity extractSeverity(ResultSet resultSet) throws SQLException {
	return Severity.valueOf(resultSet.getString("SEVERITY"));
    }

    /**
     * This method is used to extract the classification out of a single
     * {@link ResultSet}.
     * 
     * @param resultSet
     *            is the {@link ResultSet} to read the {@link Classification}
     *            from.
     * @return A {@link Classification} object is returned containing the
     *         {@link Classification}.
     * @throws SQLException
     *             is thrown in case of SQL issues.
     */
    protected Classification extractClassification(ResultSet resultSet) throws SQLException {
	return Classification.valueOf(resultSet.getString("CLASSIFICATION"));
    }

    /**
     * This method is used to extract the evaluator version out of a single
     * {@link ResultSet}.
     * 
     * @param resultSet
     *            is the {@link ResultSet} to read the {@link Version} from.
     * @return A {@link Version} object is returned containing the
     *         {@link Version}.
     * @throws SQLException
     *             is thrown in case of SQL issues.
     */
    protected Version extractEvaluatorVersion(ResultSet resultSet) throws SQLException {
	return extractVersion(resultSet, "EVALUATOR_VERSION");
    }

    /**
     * This method is used to extract the version out of a single
     * {@link ResultSet} for a specified column.
     * 
     * @param resultSet
     *            is the {@link ResultSet} to read the {@link Version} from.
     * @param columnName
     *            is the name of the column to extract the version from.
     * @return A {@link Version} object is returned containing the
     *         {@link Version}.
     * @throws SQLException
     *             is thrown in case of SQL issues.
     */
    protected Version extractVersion(ResultSet resultSet, String columnName) throws SQLException {
	return Version.valueOf(resultSet.getString(columnName));
    }

    /**
     * This method is used to extract the version out of a single
     * {@link ResultSet} for a specified column.
     * 
     * @param resultSet
     *            is the {@link ResultSet} to read the {@link Version} from.
     * @param columnIndex
     *            is the index of the column to extract the version from.
     * @return A {@link Version} object is returned containing the
     *         {@link Version}.
     * @throws SQLException
     *             is thrown in case of SQL issues.
     */
    protected Version extractVersion(ResultSet resultSet, int columnIndex) throws SQLException {
	return Version.valueOf(resultSet.getString(columnIndex));
    }

}
