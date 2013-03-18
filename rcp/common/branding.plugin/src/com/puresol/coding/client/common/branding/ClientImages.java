package com.puresol.coding.client.common.branding;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

public enum ClientImages {

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
    ANALYSIS_RUN_DELETE_16x16("/icons/16x16/analysis_run_delete.png");//

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
