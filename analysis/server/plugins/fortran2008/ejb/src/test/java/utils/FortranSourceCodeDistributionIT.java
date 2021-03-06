package utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.misc.StopWatch;
import com.puresoltechnologies.commons.misc.io.FileSearch;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.parsers.source.SourceFileLocation;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalyzer;
import com.puresoltechnologies.purifinity.server.common.utils.ConsoleUtils;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.Fortran;
import com.puresoltechnologies.trees.TreePrinter;

/**
 * This is not a real JUnit test, but it's used manually to check the parser
 * against the Java Source Code Distribution which is one of the biggest Java
 * source bundles available to check completeness and stability.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranSourceCodeDistributionIT {

	private static final String INSTALL_DIRECTORY = "/home/ludwig/workspaces/Dyn3D";

	private final static Logger logger = LoggerFactory
			.getLogger(FortranSourceCodeDistributionIT.class);

	@Test
	@Ignore
	public void test() {
		try {
			File file = new File("ndicrs.f");
			StopWatch watch = new StopWatch();
			watch.start();
			String directory = "src/fort";
			String filePath = file.getPath();
			SourceFileLocation sourceFileLocation = new SourceFileLocation(
					directory, filePath);
			try (InputStream sourceStream = sourceFileLocation.openStream()) {
				CodeAnalyzer analyser = new Fortran()
						.createAnalyser(SourceCode.read(sourceStream,
								sourceFileLocation), null);
				analyser.analyze();
				watch.stop();
				CodeAnalysis analysis = analyser.getAnalysis();
				assertTrue(analysis.getAnalysisInformation().isSuccessful());
				UniversalSyntaxTree ust = analysis.getUniversalSyntaxTree();
				assertNotNull(ust);
				System.out.print(watch.getSeconds());
				if (logger.isTraceEnabled()) {
					ByteArrayOutputStream outStream = new ByteArrayOutputStream();
					new TreePrinter(new PrintStream(outStream)).println(ust);
					logger.trace(outStream.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	private static void parseAllFiles(List<File> files) {
		StopWatch totalWatch = new StopWatch();
		totalWatch.start();
		try {
			List<File> errors = new ArrayList<File>();
			List<String> successes = new ArrayList<String>();
			RandomAccessFile raFile = new RandomAccessFile("succeeded.txt",
					"rw");
			String line;
			while ((line = raFile.readLine()) != null) {
				successes.add(line);
			}
			int counter = 0;
			StopWatch watch = new StopWatch();
			for (File file : files) {
				assertTrue(new File(INSTALL_DIRECTORY, file.toString())
						.exists());
				if (file.getName().contains("-")) {
					raFile.writeBytes(file.toString() + "\n");
					successes.add(file.toString());
					continue;
				}
				counter++;
				System.out.print(ConsoleUtils.createPercentageBar(22,
						(double) counter / (double) files.size(), true) + "\t");
				if (successes.size() > 0) {
					System.out.print((double) successes.size()
							/ (double) (successes.size() + errors.size())
							* 100.0);
					System.out.print("%\t");
				}
				System.out
						.print(Runtime.getRuntime().freeMemory() / 1024 / 1024);
				System.out.print("MB\t");
				System.out.print(file + "\t");
				if (successes.contains(file.toString())) {
					System.out.println();
					continue;
				}
				watch.start();
				if (parseFile(new File(INSTALL_DIRECTORY), file)) {
					raFile.writeBytes(file.toString() + "\n");
					successes.add(file.toString());
				} else {
					errors.add(file);
				}
				watch.stop();
				System.out.println(watch.toString() + "s");
			}
			raFile.close();
			System.out.println("\n\nERRORS FOR FILES:\n\n");
			for (File file : errors) {
				System.out.println(file);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		totalWatch.stop();
		System.out.println("\n\nTotal time:\t" + totalWatch.toString());
	}

	private static boolean parseFile(File sourceDirectory, File file) {
		try {
			Fortran fortran = new Fortran();
			SourceFileLocation sourceFileLocation = new SourceFileLocation(
					sourceDirectory, file);
			try (InputStream sourceStream = sourceFileLocation.openStream()) {
				CodeAnalyzer analyser = fortran
						.createAnalyser(SourceCode.read(sourceStream,
								sourceFileLocation), null);
				analyser.analyze();
				boolean result = analyser.getAnalysis()
						.getAnalysisInformation().isSuccessful();
				analyser = null;
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String args[]) {
		List<File> files = FileSearch.find(new File(INSTALL_DIRECTORY), "*.f");
		parseAllFiles(files);
	}

}
