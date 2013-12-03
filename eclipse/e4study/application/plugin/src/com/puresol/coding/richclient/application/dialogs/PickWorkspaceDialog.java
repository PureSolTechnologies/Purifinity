package com.puresol.coding.richclient.application.dialogs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * This dialog provides the facility to select a workspace. This dialog is used
 * for the startup dialog when no workspace was selected, yet, and when the
 * workspace is to be switched.
 * 
 * The samples was found at: http://hexapixel.com/2009/01/12/rcp-workspaces
 * 
 * IMPORTANT: The option "-data @noDefault" needs to be set for the workspace
 * setting to be working!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PickWorkspaceDialog extends TitleAreaDialog {

	private static final String LOCATION_LIST_SEPARATOR = ";;;";
	private static final int MAX_LOCATION_HISTORY = 20;

	private static final String _KeyWorkspaceRootDir = "workspace.location";
	private static final String _KeyRememberWorkspace = "workspace.remember";
	private static final String _KeyLastUsedWorkspaces = "workspace.lastUsedWorkspaces";

	private static Preferences preferences = Preferences
			.userNodeForPackage(PickWorkspaceDialog.class);

	public static String getWorkspaceLocation() {
		boolean isRemembering = preferences.getBoolean(_KeyRememberWorkspace,
				false);
		if (isRemembering) {
			return preferences.get(_KeyWorkspaceRootDir, null);
		}
		return null;
	}

	private final boolean isSwitchWorkspace;

	private Combo comboLocation;
	private Button btnRememberLocation;

	private boolean rememberLocation = false;
	private String location = "";
	private final List<String> lastLocations = new ArrayList<String>();

	public PickWorkspaceDialog(boolean isSwitchWorkspace) {
		super(Display.getDefault().getActiveShell());
		this.isSwitchWorkspace = isSwitchWorkspace;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if (isSwitchWorkspace) {
			newShell.setText("Switch Workspace");
		} else {
			newShell.setText("Workspace Selection");
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite superComposite = (Composite) super.createDialogArea(parent);

		setTitle("Pick Workspace");
		setMessage("Specify the location of the workspace where analysis projects and settings are to be stored.");

		Composite locationComposite = new Composite(superComposite, SWT.NONE);
		locationComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false,
				false, 1, 1));
		locationComposite.setLayout(new GridLayout(3, false));

		Label lblLocation = new Label(locationComposite, SWT.NONE);
		lblLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		lblLocation.setAlignment(SWT.CENTER);
		lblLocation.setText("Workspace:");

		comboLocation = new Combo(locationComposite, SWT.NONE);
		comboLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Button btnBrowse = new Button(locationComposite, SWT.NONE);
		btnBrowse.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		btnBrowse.setText("Browse...");
		new Label(locationComposite, SWT.NONE);

		btnRememberLocation = new Button(locationComposite, SWT.CHECK);
		btnRememberLocation.setText("Remember Workspace Location");
		new Label(locationComposite, SWT.NONE);
		btnBrowse.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				browse();
			}

		});

		readSettings();

		return superComposite;
	}

	private void browse() {
		Shell shell = Display.getDefault().getActiveShell();
		DirectoryDialog dialog = new DirectoryDialog(shell);
		dialog.setText("Select Workspace Directory...");
		String location = dialog.open();
		if (location != null)
			comboLocation.setText(location);
	}

	private void readSettings() {
		location = preferences.get(_KeyWorkspaceRootDir, null);
		if (location != null) {
			comboLocation.setText(location);
		}

		rememberLocation = preferences.getBoolean(_KeyRememberWorkspace, false);
		btnRememberLocation.setSelection(rememberLocation);

		String lastLocationsString = preferences
				.get(_KeyLastUsedWorkspaces, "");
		for (String lastLocation : lastLocationsString
				.split(LOCATION_LIST_SEPARATOR)) {
			comboLocation.add(lastLocation);
			lastLocations.add(lastLocation);
		}
	}

	private void saveSettings() {
		preferences.putBoolean(_KeyRememberWorkspace, isRememberLocation());
		preferences.put(_KeyWorkspaceRootDir, getLocation());
		lastLocations.remove(getLocation());
		lastLocations.add(getLocation());
		while (lastLocations.size() > MAX_LOCATION_HISTORY) {
			lastLocations.remove(0);
		}
		StringBuilder builder = new StringBuilder();
		for (String lastLocation : lastLocations) {
			if (builder.length() > 0) {
				builder.append(LOCATION_LIST_SEPARATOR);
			}
			builder.append(lastLocation);
		}
		preferences.put(_KeyLastUsedWorkspaces, builder.toString());
	}

	@Override
	public void okPressed() {
		rememberLocation = btnRememberLocation.getSelection();
		location = comboLocation.getText();
		if (location == null) {
			return;
		}
		File directory = new File(location);
		if ((directory.exists()) && (directory.isDirectory())) {
			saveSettings();
			super.okPressed();
		}
	}

	public String getLocation() {
		return location;
	}

	public boolean isRememberLocation() {
		return rememberLocation;
	}
}
