/***************************************************************************
 *
 *   ProjectAnalyserTest.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;
import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ProjectAnalyserTest extends TestCase {

	@Test
	public void testSerialization() {
		try {
			ProjectAnalyser projectAnalyser = TestProjectAnalysers.MINIMAL_PROJECT_ANALYSER;

			Persistence.persist(projectAnalyser, new File("test/persist.test"));
			ProjectAnalyser restored = (ProjectAnalyser) Persistence
					.restore(new File("test/persist.test"));

			Assert.assertNotSame(projectAnalyser, restored);
			Assert.assertEquals(projectAnalyser, restored);
		} catch (PersistenceException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public void testFortran() {
		ProjectAnalyser analyser = new ProjectAnalyser(new File(
				"/usr/src/compile/ATLAS/src/blas/f77reference"), "**/zgerc.f");
		analyser.run();
		for (File file : analyser.getFiles()) {
			List<CodeRange> ranges = analyser.getAnalyser(file)
					.getNamedCodeRanges();
			for (CodeRange range : ranges) {
				TokenStream stream = range.getTokenStream();
				for (int index = 0; index < stream.getSize(); index++) {
					Token token = stream.get(index);
					if (token.getPublicity() == TokenPublicity.HIDDEN) {
						continue;
					}
					System.out.print("'");
					System.out.print(token.getText());
					System.out.print("'");
					System.out.println("\t(" + token.getStartLine() + " / "
							+ token.getDefinition().toString() + ")");
				}
			}
		}
	}
}
