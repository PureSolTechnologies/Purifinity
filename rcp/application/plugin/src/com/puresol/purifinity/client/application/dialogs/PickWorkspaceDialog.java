package com.puresol.purifinity.client.application.dialogs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
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

	private static final int DEFAULT_MARGIN = 5;

	private static final String _KeyWorkspaceRootDir = "workspace.location";
	private static final String _KeyRememberWorkspace = "workspace.remember";
	private static final String _KeyLastUsedWorkspaces = "workspace.lastUsedWorkspaces";

	private static Preferences preferences = Preferences
			.userNodeForPackage(PickWorkspaceDialog.class);

	/**
	 * This static method returns the workspace location as string as it is got
	 * from preference store.
	 * 
	 * @return A {@link String} is returned containing the workspace directory.
	 */
	public static String getWorkspaceLocation() {
		boolean isRemembering = preferences.getBoolean(_KeyRememberWorkspace,
				false);
		String workspaceDirectory = null;
		if (isRemembering) {
			workspaceDirectory = preferences.get(_KeyWorkspaceRootDir, null);
		}
		return workspaceDirectory;
	}

	private final boolean isSwitchWorkspace;

	private Combo locationCombo;
	private Button rememberLocationButton;

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
	public void create() {
		super.create();
		setTitle("Pick Workspace");
		setMessage("Specify the location of the workspace where analysis projects and settings are to be stored.");
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		final Composite dialogArea = (Composite) super.createDialogArea(parent);

		Composite locationComposite = new Composite(dialogArea, SWT.NONE);
		locationComposite.setLayout(new FormLayout());
		locationComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label locationLabel = new Label(locationComposite, SWT.NONE);
		locationLabel.setText("Workspace Directory:");

		locationCombo = new Combo(locationComposite, SWT.NONE);

		Button browseButton = new Button(locationComposite, SWT.NONE);
		browseButton.setText("Browse...");

		FormData fdLocationLabel = new FormData();
		fdLocationLabel.top = new FormAttachment(0, DEFAULT_MARGIN);
		fdLocationLabel.left = new FormAttachment(0, DEFAULT_MARGIN);
		locationLabel.setLayoutData(fdLocationLabel);

		FormData fdLocationCombo = new FormData();
		fdLocationCombo.left = new FormAttachment(0, DEFAULT_MARGIN);
		fdLocationCombo.top = new FormAttachment(locationLabel, DEFAULT_MARGIN);
		fdLocationCombo.right = new FormAttachment(browseButton,
				-DEFAULT_MARGIN);
		locationCombo.setLayoutData(fdLocationCombo);

		FormData fdBrowseButton = new FormData();
		fdBrowseButton.top = new FormAttachment(locationCombo, 0, SWT.TOP);
		fdBrowseButton.right = new FormAttachment(100, -DEFAULT_MARGIN);
		browseButton.setLayoutData(fdBrowseButton);

		rememberLocationButton = new Button(locationComposite, SWT.CHECK);
		rememberLocationButton.setText("Remember Workspace Location");
		FormData fdRememberLocationButton = new FormData();
		fdRememberLocationButton.top = new FormAttachment(locationCombo,
				DEFAULT_MARGIN, SWT.BOTTOM);
		fdRememberLocationButton.left = new FormAttachment(locationCombo, 0,
				SWT.LEFT);
		fdRememberLocationButton.right = new FormAttachment(100,
				-DEFAULT_MARGIN);
		rememberLocationButton.setLayoutData(fdRememberLocationButton);

		browseButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				browse();
			}

		});

		locationComposite.pack();

		readSettings();

		return dialogArea;
	}

	private void browse() {
		Shell shell = Display.getDefault().getActiveShell();
		DirectoryDialog dialog = new DirectoryDialog(shell);
		dialog.setText("Select Workspace Directory...");
		String location = dialog.open();
		if (location != null)
			locationCombo.setText(location);
	}

	private void readSettings() {
		location = preferences.get(_KeyWorkspaceRootDir, null);
		if (location != null) {
			locationCombo.setText(location);
		}

		rememberLocation = preferences.getBoolean(_KeyRememberWorkspace, false);
		rememberLocationButton.setSelection(rememberLocation);

		String lastLocationsString = preferences
				.get(_KeyLastUsedWorkspaces, "");
		for (String lastLocation : lastLocationsString
				.split(LOCATION_LIST_SEPARATOR)) {
			locationCombo.add(lastLocation);
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
		rememberLocation = rememberLocationButton.getSelection();
		location = locationCombo.getText();
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
