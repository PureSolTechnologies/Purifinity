/***************************************************************************
 *
 *   CodeAnalysis.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package apps;

import java.io.File;

import com.puresol.jars.JarLoader;
import com.puresol.utils.ConsoleUtils;
import com.puresol.utils.DirectoryUtilities;

/**
 * This is PureSolTechnologies' code analysis tool for automated source code
 * validation and evaluation.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CodeAnalysisStarter {

	private static void showEnvironmentConditions() {
		ConsoleUtils.printSystemProperties();
		ConsoleUtils.printEnvironment();
	}

	private static void loadLibraries() {
		File installDir = DirectoryUtilities.getInstallationDirectory(
				CodeAnalysisStarter.class, true);
		File libDir = new File(installDir, "lib");
		JarLoader.loadJarsFromDirectory(libDir, true);
	}

	public static void main(String[] args) {
		showEnvironmentConditions();
		loadLibraries();
		new CodeAnalysis().run();
	}
}
