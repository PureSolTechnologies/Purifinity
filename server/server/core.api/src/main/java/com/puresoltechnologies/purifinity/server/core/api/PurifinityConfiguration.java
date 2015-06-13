package com.puresoltechnologies.purifinity.server.core.api;

import java.util.List;

import javax.inject.Singleton;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;

/**
 * This interface is used to provide the configuration for the Purifinity
 * system.
 * 
 * @author Rick-Rainer Ludwig
 */
@Singleton
public interface PurifinityConfiguration {

	public static final ConfigurationParameter<Boolean> ANONYMOUS_CAN_READ = new ConfigurationParameter<>(
			"Anonymous can read",
			"",
			LevelOfMeasurement.NOMINAL,
			"Specifies whether an anonymous user (a user not logged in) is allowed to see results in read only mode.",
			Boolean.class, "anonymous.can.read", "/Security", false);

	public static final ConfigurationParameter<Integer> ALERT_MESSAGE_TIMEOUT = new ConfigurationParameter<>(
			"Alert Message Timeout", "s", LevelOfMeasurement.RATIO,
			"Specifies the timeout for messages shown by Alerter.",
			Integer.class, "webui.messages.timeout", "/WebUI", 3);

	public static final ConfigurationParameter<Integer> USER_INACTIVITY_TIMEOUT = new ConfigurationParameter<>(
			"User Inactivity Timeout",
			"min",
			LevelOfMeasurement.RATIO,
			"Specifies the time in minutes after which an inactive user is automatically logged off.",
			Integer.class, "user.login.timeout.inactive", "/Security", 15);

	public static final ConfigurationParameter<Integer> USER_SESSION_TIMEOUT = new ConfigurationParameter<>(
			"User Session Timeout",
			"min",
			LevelOfMeasurement.RATIO,
			"Specifies the time in minutes after which an user is automatically logged off for re-login.",
			Integer.class, "user.login.timeout.session", "/Security", 1440);

	public static final ConfigurationParameter<Long> MAX_FILE_SIZE = new ConfigurationParameter<>(
			"Maximum File Size",
			"byte",
			LevelOfMeasurement.RATIO,
			"Specifies the maximum file size which shall be stored into databse..",
			Long.class, "storage.file.size.max", "/Storage", 1024l * 1024l);

	/**
	 * This method returns a list of all configuration parameters.
	 * 
	 * @return A {@link List} of {@link ConfigurationParameter} is returned.
	 */
	public List<ConfigurationParameter<?>> getParameters();

}
