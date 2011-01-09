package apps;

import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.i18n4java.Translator;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swingx.AboutBox;
import javax.swingx.Application;
import javax.swingx.Menu;
import javax.swingx.MenuBar;
import javax.swingx.MenuItem;
import javax.swingx.Saveable;
import javax.swingx.SaveableCodeViewer;
import javax.swingx.ScrollPane;
import javax.swingx.TabbedPane;
import javax.swingx.config.APIInformation;
import javax.swingx.connect.Slot;

import com.puresol.gui.TreeViewer;
import com.puresol.gui.uhura.ASCIITreeViewer;
import com.puresol.gui.uhura.GrammarSchematic;
import com.puresol.trees.TreeException;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarConverter;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarFile;
import com.puresol.utils.FileUtilities;

public class GrammarViewer extends Application {

	private static final long serialVersionUID = -5304464400142172055L;

	private static final Translator translator = Translator
			.getTranslator(GrammarViewer.class);

	private File lastOpenDirectory = new File(System.getProperty("user.dir"));
	private File lastSaveLocation = new File(System.getProperty("user.dir"));
	private File grammarFile = null;
	private Grammar grammar = null;
	private TabbedPane tabbedPane;
	private SaveableCodeViewer source;
	private SaveableCodeViewer bnf;
	private ASCIITreeViewer ast;
	private GrammarSchematic schematic;
	private TreeViewer<ParserTree> parserTreeViewer;

	public GrammarViewer() {
		super("Nyota Uhura Grammar Viewer", APIInformation.getPackageVersion());
		initUI();

		// TODO the following line(s) are to be removed!
		open(new File(
				"/home/ludwig/workspace/FortranLanguagePack/src/com/puresol/coding/lang/fortran/grammar/Fortran2008.g"));

	}

	private void initUI() {
		MenuBar menubar = new MenuBar();

		Menu fileMenu = new Menu("File");
		menubar.add(fileMenu);

		MenuItem openGrammarItem = new MenuItem("Open grammar...");
		openGrammarItem.connect("start", this, "openGrammar");
		fileMenu.add(openGrammarItem);

		fileMenu.addSeparator();

		MenuItem saveItem = new MenuItem("Save...");
		saveItem.connect("start", this, "save");
		fileMenu.add(saveItem);

		fileMenu.addSeparator();

		MenuItem quitItem = new MenuItem("Quit...");
		quitItem.connect("start", this, "quit");
		fileMenu.add(quitItem);

		menubar.add(Box.createHorizontalGlue());

		Menu helpMenu = new Menu("Help");
		menubar.add(helpMenu);

		MenuItem aboutItem = new MenuItem("About...");
		aboutItem.connect("start", this, "about");
		helpMenu.add(aboutItem);

		setJMenuBar(menubar);

		tabbedPane = new TabbedPane();
		getContentPane().add(tabbedPane);

		source = new SaveableCodeViewer();
		source.setEditable(false);
		tabbedPane.add(new ScrollPane(source), "Source");

		bnf = new SaveableCodeViewer();
		bnf.setEditable(false);
		tabbedPane.add(new ScrollPane(bnf), "BNF (Backus Naur Form)");

		ast = new ASCIITreeViewer();
		tabbedPane.add(new ScrollPane(ast), "ASCII Tree");

		parserTreeViewer = new TreeViewer<ParserTree>();
		tabbedPane.add(new ScrollPane(parserTreeViewer), "Parser Tree Viewer");

		schematic = new GrammarSchematic();
		schematic.setPreferredSize(new Dimension(100, 100));
		tabbedPane.add(new ScrollPane(schematic), "Schematic");
	}

	@Slot
	@SuppressWarnings("unused")
	private void openGrammar() {
		JFileChooser fileChooser = new JFileChooser(lastOpenDirectory);
		fileChooser.setDialogTitle("Open Grammar File");
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			open(fileChooser.getSelectedFile());
		}
	}

	private void open(File file) {
		grammarFile = file;
		lastOpenDirectory = grammarFile;
		setSubtitle(file.getName());
		refreshGrammar();
	}

	@Slot
	@SuppressWarnings("unused")
	private void save() {
		File file = null;
		try {
			Component component = tabbedPane.getSelectedComponent();
			System.out.println(component.getClass().getName());
			if (component == null) {
				return;
			}
			if (ScrollPane.class.isAssignableFrom(component.getClass())) {
				component = ((ScrollPane) component).getViewport().getView();
			}
			if (!Saveable.class.isAssignableFrom(component.getClass())) {
				return;
			}
			Saveable saveable = (Saveable) component;
			JFileChooser fileChooser = new JFileChooser(lastSaveLocation);
			FileFilter filters[] = saveable.getPossibleFileFilters();
			if (filters != null) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				for (FileFilter fileFilter : filters) {
					fileChooser.addChoosableFileFilter(fileFilter);
				}
			} else {
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			}
			fileChooser.setDialogTitle("Save...");
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
				lastSaveLocation = file;
				saveable.save(file);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,
					translator.i18n("Could not write file {0}", file),
					translator.i18n("Error"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void refreshGrammar() {
		try {
			String text = FileUtilities.readFileToString(grammarFile);
			source.setText(text);

			GrammarFile grammarFile = new GrammarFile(new StringReader(text));

			ast.setGrammarAST(grammarFile.getAST());
			schematic.setGrammarAST(grammarFile.getAST());
			parserTreeViewer.setTreeData(grammarFile.getAST());

			GrammarConverter converter = new GrammarConverter(
					grammarFile.getAST());
			grammar = converter.getGrammar();
			if (grammar == null) {
				bnf.removeAll();
			} else {
				bnf.setText(grammar.toString());
			}
		} catch (IOException e) {
			JOptionPane
					.showMessageDialog(this, translator.i18n(
							"File {0} could not be read!", grammarFile),
							translator.i18n("error"), JOptionPane.ERROR_MESSAGE);
		} catch (GrammarException e) {
			JOptionPane.showMessageDialog(this, translator.i18n(
					"File {0} contains an invalid grammar!\nMessage:\n{1}",
					grammarFile, e.getMessage()), translator.i18n("error"),
					JOptionPane.ERROR_MESSAGE);
		} catch (TreeException e) {
			JOptionPane.showMessageDialog(this, translator.i18n(
					"File {0} contains an invalid grammar!\nMessage:\n{1}",
					grammarFile, e.getMessage()), translator.i18n("error"),
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Slot
	@SuppressWarnings("unused")
	private void about() {
		AboutBox.about();
	}

	public static void main(String[] args) {
		new GrammarViewer().run();
	}

}
