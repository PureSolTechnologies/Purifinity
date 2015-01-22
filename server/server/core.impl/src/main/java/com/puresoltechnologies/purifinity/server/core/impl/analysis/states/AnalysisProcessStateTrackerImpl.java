package com.puresoltechnologies.purifinity.server.core.impl.analysis.states;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessState;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessStateTracker;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessStatusInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessTransition;
import com.puresoltechnologies.purifinity.server.database.cassandra.ProcessStatesKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;

public class AnalysisProcessStateTrackerImpl implements
		AnalysisProcessStateTracker {

	@Inject
	@ProcessStatesKeyspace
	private Session session;

	@Inject
	private CassandraPreparedStatements preparedStatements;

	@Override
	public void startProcess(UUID projectUUID) {
		AnalysisProcessStatusInformation state = readProcessState(projectUUID);
		if (state != null) {
			throw new IllegalStateException(
					"Process(es) was/were found for project UUID '"
							+ projectUUID + "'.");
		}
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(
						session,
						"INSERT INTO analysis_process (started, last_progress, project_uuid, state) VALUES (?, ?, ?, ?);");
		Date started = new Date();
		BoundStatement boundStatement = preparedStatement.bind(started,
				started, projectUUID,
				AnalysisProcessState.QUEUED_FOR_START.name());
		session.execute(boundStatement);
	}

	@Override
	public List<AnalysisProcessStatusInformation> readProcessStates() {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(
						session,
						"SELECT project_uuid, run_uuid, state, started, last_progress FROM analysis_process;");
		BoundStatement boundStatement = preparedStatement.bind();
		ResultSet resultSet = session.execute(boundStatement);
		List<AnalysisProcessStatusInformation> information = new ArrayList<>();
		while (!resultSet.isExhausted()) {
			Row row = resultSet.one();
			UUID projectUUID = row.getUUID(0);
			UUID runUUID = row.getUUID(1);
			String stateString = row.getString(2);
			Date started = row.getDate(3);
			Date lastProgress = row.getDate(4);
			AnalysisProcessState state = AnalysisProcessState
					.valueOf(stateString);
			information.add(new AnalysisProcessStatusInformation(started,
					projectUUID, runUUID, state, lastProgress));
		}
		return information;
	}

	@Override
	public AnalysisProcessStatusInformation readProcessState(UUID projectUUID) {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(
						session,
						"SELECT run_uuid, state, started, last_progress FROM analysis_process WHERE project_uuid=?;");
		BoundStatement boundStatement = preparedStatement.bind(projectUUID);
		ResultSet resultSet = session.execute(boundStatement);
		if (resultSet.isExhausted()) {
			return null;
		}
		Row row = resultSet.one();
		if (!resultSet.isExhausted()) {
			throw new IllegalStateException(
					"More than one process was found for project UUID '"
							+ projectUUID + "'.");
		}
		UUID runUUID = row.getUUID(0);
		String stateString = row.getString(1);
		AnalysisProcessState state = AnalysisProcessState.valueOf(stateString);
		Date started = row.getDate(2);
		Date lastProgress = row.getDate(3);
		return new AnalysisProcessStatusInformation(started, projectUUID,
				runUUID, state, lastProgress);
	}

	@Override
	public boolean changeProcessState(UUID projectUUID, UUID runUUID,
			AnalysisProcessTransition transition) {
		AnalysisProcessStatusInformation statusInformation = readProcessState(projectUUID);
		if (statusInformation == null) {
			return false;
		}
		AnalysisProcessState state = statusInformation.getState();
		AnalysisProcessStateModel model = new AnalysisProcessStateModel(state);
		if (!model.canPerformTransition(transition)) {
			return false;
		}
		model.performTransition(transition);
		setProcessState(projectUUID, runUUID, model.getState());
		return true;
	}

	private void setProcessState(UUID projectUUID, UUID runUUID,
			AnalysisProcessState state) {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(
						session,
						"INSERT INTO analysis_process (project_uuid, run_uuid, state, last_progress) VALUES (?, ?, ?, ?);");
		BoundStatement boundStatement = preparedStatement.bind(projectUUID,
				runUUID, state.name(), new Date());
		session.execute(boundStatement);
	}

	@Override
	public void stopProcess(UUID projectUUID) {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(session,
						"DELETE FROM analysis_process WHERE project_uuid = ?;");
		BoundStatement boundStatement = preparedStatement.bind(projectUUID);
		session.execute(boundStatement);
	}
}
