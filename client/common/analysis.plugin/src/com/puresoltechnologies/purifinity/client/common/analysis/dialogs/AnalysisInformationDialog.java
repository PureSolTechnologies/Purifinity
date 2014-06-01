package com.puresoltechnologies.purifinity.client.common.analysis.dialogs;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.CloseWindowListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;

public class AnalysisInformationDialog extends Dialog {

	private AnalysisRun analyzedRun;
	private AnalysisInformation analyzedCode;
	private Text fileName;
	private Text filePath;
	private Text message;
	private final List<WeakReference<CloseWindowListener>> closeListeners = new ArrayList<WeakReference<CloseWindowListener>>();

	public AnalysisInformationDialog(ViewPart part, AnalysisRun analyzedRun,
			AnalysisInformation analyzedCode) {
		super(part.getSite());
		setShellStyle(SWT.TITLE);
		this.analyzedRun = analyzedRun;
		this.analyzedCode = analyzedCode;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.VERTICAL));

		Composite fileInformation = new Composite(container, SWT.BORDER);
		fileInformation.setLayout(new GridLayout(2, false));
		new Label(fileInformation, SWT.NONE).setText("Path:");
		filePath = new Text(fileInformation, SWT.BORDER | SWT.READ_ONLY);
		new Label(fileInformation, SWT.NONE).setText("Name:");
		fileName = new Text(fileInformation, SWT.BORDER | SWT.READ_ONLY);

		message = new Text(container, SWT.BORDER | SWT.MULTI);
		refresh();
		return container;
	}

	@Override
	protected final void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.CLOSE_ID,
				IDialogConstants.CLOSE_LABEL, true);
	}

	@Override
	protected final void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.CLOSE_ID) {
			for (WeakReference<CloseWindowListener> listener : closeListeners) {
				if (listener.get() != null) {
					listener.get().close(new WindowEvent(this.getShell()));
				}
			}
			close();
		}
	}

	@Override
	protected final void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Analysis Information");
	}

	public void setAnalyzedCode(AnalysisRun analyzedRun,
			AnalysisInformation analyzedCode) {
		this.analyzedRun = analyzedRun;
		this.analyzedCode = analyzedCode;
		refresh();
	}

	private void refresh() {
		SourceCodeLocation sourceCodeLocation = analyzedRun.findTreeNode(
				analyzedCode.getHashId()).getSourceCodeLocation();
		filePath.setText(sourceCodeLocation.getInternalLocation());
		fileName.setText(sourceCodeLocation.getName());
		String messageText = analyzedCode.getAnalyzerErrorMessage();
		if (messageText != null) {
			message.setText(messageText);
		} else {
			message.setText("");
		}
	}

	public void addCloseListener(CloseWindowListener closeWindowListener) {
		closeListeners.add(new WeakReference<CloseWindowListener>(
				closeWindowListener));
	}

	public void removeCloseListener(CloseWindowListener closeWindowListener) {
		Iterator<WeakReference<CloseWindowListener>> iterator = closeListeners
				.iterator();
		while (iterator.hasNext()) {
			WeakReference<CloseWindowListener> listener = iterator.next();
			if ((listener.get() == null)
					|| (listener.get() == closeWindowListener)) {
				iterator.remove();
			}
		}
	}
}
