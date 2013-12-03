package com.puresoltechnologies.purifinity.client.common.branding;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

public enum ClientImages {

	APPLICATION_GO_16x16("/icons/16x16/application_go.png"), //
	BUG_16x16("/icons/16x16/bug.png"), //
	FIND_16x16("/icons/16x16/find.png"), //
	//
	ARROW_REFRESH_16x16("/icons/16x16/arrow_refresh.png"), //
	//
	DATABASE_REFRESH_16x16("/icons/16x16/database_refresh.png"), //
	FOLDER_16x16("/icons/16x16/folder.png"), //
	FOLDER_ADD_16x16("/icons/16x16/folder_add.png"), //
	FOLDER_EDIT_16x16("/icons/16x16/folder_edit.png"), //
	FOLDER_DELETE_16x16("/icons/16x16/folder_delete.png"), //
	FOLDERS_16x16("/icons/16x16/folders.png"), //
	FOLDERS_EXPLORER_16x16("/icons/16x16/folders_explorer.png"), //
	DATABASE_FOLDER_16x16("/icons/16x16/folder_database.png"), //
	DOCUMENT_EMPTY_16x16("/icons/16x16/document_empty.png"), //
	DOCUMENT_EXPORT_16x16("/icons/16x16/document_export.png"),
	//
	ANALYSIS_16x16("/icons/16x16/analysis.png"), //
	ANALYZES_VIEW_16x16("/icons/16x16/analyzes_view.png"), //
	ANALYSIS_ADD_16x16("/icons/16x16/analysis_add.png"), //
	ANALYSIS_EDIT_16x16("/icons/16x16/analysis_edit.png"), //
	ANALYSIS_DELETE_16x16("/icons/16x16/analysis_delete.png"), //
	//
	ANALYSIS_RUN_16x16("/icons/16x16/analysis_run.png"), //
	ANALYSIS_RUNS_VIEW_16x16("/icons/16x16/analysis_runs_view.png"), //
	ANALYSIS_RUN_ADD_16x16("/icons/16x16/analysis_run_add.png"), //
	ANALYSIS_RUN_EDIT_16x16("/icons/16x16/analysis_run_edit.png"), //
	ANALYSIS_RUN_DELETE_16x16("/icons/16x16/analysis_run_delete.png"), //
	//
	TAB_16x16("/icons/16x16/tab.png"), //
	TAB_ADD_16x16("/icons/16x16/tab_add.png"), //
	TAB_DELETE_16x16("/icons/16x16/tab_delete.png"), //
	TAB_EDIT_16x16("/icons/16x16/tab_edit.png"), //
	TAB_GO_16x16("/icons/16x16/tab_go.png"), //
	//
	INFORMATION_16x16("/icons/16x16/information.png"), //
	//
	WRENCH_ORANGE_16x16("/icons/16x16/wrench_orange.png"), //
	//
	DEC_HIGH_QUALITY_8x8("/icons/8x8/dec_high_quality.png"), //
	DEC_MEDIUM_QUALITY_8x8("/icons/8x8/dec_medium_quality.png"), //
	DEC_LOW_QUALITY_8x8("/icons/8x8/dec_low_quality.png"), //
	;

	private static final String PLUGIN_ID = ClientImages.class.getPackage()
			.getName() + ".plugin";

	private final String path;

	private ClientImages(String path) {
		this.path = path;
	}

	/* default */String getPath() {
		return path;
	}

	/**
	 * This method returns the {@link Image} for the given ClientImage.
	 * 
	 * @param image
	 *            specified the image to be loaded.
	 * @return Returns an {@link Image}.
	 */
	public static Image getImage(ClientImages image) {
		return ResourceManager.getPluginImage(PLUGIN_ID, image.getPath());
	}

	/**
	 * This method returns the {@link ImageDescriptor} for the given
	 * ClientImage.
	 * 
	 * @param image
	 *            specified the image to be loaded.
	 * @return Returns an {@link ImageDescriptor}.
	 */
	public static ImageDescriptor getImageDescriptor(ClientImages image) {
		return ResourceManager.getPluginImageDescriptor(PLUGIN_ID,
				image.getPath());
	}
}
