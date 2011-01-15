package apps;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.i18n4java.Translator;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileFilter;

import com.puresol.config.APIInformation;
import com.puresol.gui.PureSolApplication;
import com.puresol.gui.Saveable;
import com.puresol.gui.SaveableCodeViewer;
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

public class GrammarViewer extends PureSolApplication implements ActionListener {

	private static final long serialVersionUID = -5304464400142172055L;

	private static final Translator translator = Translator
			.getTranslator(GrammarViewer.class);

	private final JTabbedPane tabbedPane = new JTabbedPane();
	private final JMenuItem openGrammarItem = new JMenuItem("Open grammar...");
	private final JMenuItem saveItem = new JMenuItem("Save...");
	private final JMenuItem quitItem = new JMenuItem("Quit...");

	private File lastOpenDirectory = new File(System.getProperty("user.dir"));
	private File lastSaveLocation = new File(System.getProperty("user.dir"));
	private File grammarFile = null;
	private Grammar grammar = null;
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
				"/home/ludwig/workspace/FortranLanguagePack/src/main/resources/com/puresol/coding/lang/fortran/grammar/Fortran2008.g"));

	}

	private void initUI() {
		JMenuBar menubar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);

		openGrammarItem.addActionListener(this);
		saveItem.addActionListener(this);
		quitItem.addActionListener(this);

		fileMenu.add(openGrammarItem);
		fileMenu.addSeparator();
		fileMenu.add(saveItem);
		fileMenu.addSeparator();
		fileMenu.add(quitItem);

		menubar.add(Box.createHorizontalGlue());

		setJMenuBar(menubar);

		getContentPane().add(tabbedPane);

		source = new SaveableCodeViewer();
		source.setEditable(false);
		tabbedPane.add(new JScrollPane(source), "Source");

		bnf = new SaveableCodeViewer();
		bnf.setEditable(false);
		tabbedPane.add(new JScrollPane(bnf), "BNF (Backus Naur Form)");

		ast = new ASCIITreeViewer();
		tabbedPane.add(new JScrollPane(ast), "ASCII Tree");

		parserTreeViewer = new TreeViewer<ParserTree>();
		tabbedPane.add(new JScrollPane(parserTreeViewer), "Parser Tree Viewer");

		schematic = new GrammarSchematic();
		schematic.setPreferredSize(new Dimension(100, 100));
		tabbedPane.add(new JScrollPane(schematic), "Schematic");
	}

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

	private void save() {
		File file = null;
		try {
			Component component = tabbedPane.getSelectedComponent();
			if (component == null) {
				return;
			}
			System.out.println(component.getClass().getName());
			if (JScrollPane.class.isAssignableFrom(component.getClass())) {
				component = ((JScrollPane) component).getViewport().getView();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openGrammarItem) {
			openGrammar();
		} else if (e.getSource() == saveItem) {
			save();
		} else if (e.getSource() == quitItem) {
			quit();
		} else {
			super.actionPerformed(e);
		}
	}

	public static void main(String[] args) {
		new GrammarViewer().run();
	}

}
