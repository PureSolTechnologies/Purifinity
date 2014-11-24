package com.puresoltechnologies.purifinity.server.common.plugins;

import java.io.Serializable;
import java.net.URL;

import com.puresoltechnologies.commons.types.Version;

/**
 * This interface is used to provide information about a plugin.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PluginInformation implements Serializable {

	private static final long serialVersionUID = -3995466859627895238L;

	private final String id;
	private final String name;
	private final Version version;
	private final String description;
	private final String vendor;
	private final URL vendorURL;
	private final String pathToUI;

	public PluginInformation(String id, String name, Version version,
			String description, String vendor, URL vendorURL, String pathToUI) {
		super();
		this.id = id;
		this.name = name;
		this.version = version;
		this.description = description;
		this.vendor = vendor;
		this.vendorURL = vendorURL;
		this.pathToUI = pathToUI;
	}

	/**
	 * This method returns the plugin's id used for internal identification.
	 * 
	 * @return A {@link String} with the id is returned. A <code>null</code>
	 *         must not returned.
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method returned the plugin's name as it is to be presented for the
	 * user.
	 * 
	 * @return A {@link String} with the name is returned. A <code>null</code>
	 *         must not returned.
	 */

	public String getName() {
		return name;
	}

	/**
	 * This method returns the plugin's version.
	 * 
	 * @return A {@link Version} object is returned providing the version. A
	 *         <code>null</code> must not returned.
	 */
	public Version getVersion() {
		return version;
	}

	/**
	 * This method returns the plugin's description as it is to be displayed in
	 * short description boxes.
	 * 
	 * @return A {@link String} with the short description is returned. A
	 *         <code>null</code> must not returned. If there is no description,
	 *         provide an empty string.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method returns the name of the plugin's vendor.
	 * 
	 * @return A {@link String} is returned containing the name of the vendor. A
	 *         <code>null</code> must not returned.
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * This method returns a URL to the plugin's vendor home page.
	 * 
	 * @return An {@link URL} object is returned. A <code>null</code> value is
	 *         allowed here if not URL is to be displayed. But, a URL is
	 *         encouraged.
	 */
	public URL getVendorURL() {
		return vendorURL;
	}

	/**
	 * This method returns the path part of the URL where the plugin provides a
	 * UI which is part of Purifinity's UI.
	 * 
	 * @return A {@link String} with the path part of the plugin is returned.
	 */
	public String getPathToUI() {
		return pathToUI;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((pathToUI == null) ? 0 : pathToUI.hashCode());
		result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
		result = prime * result
				+ ((vendorURL == null) ? 0 : vendorURL.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PluginInformation other = (PluginInformation) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pathToUI == null) {
			if (other.pathToUI != null)
				return false;
		} else if (!pathToUI.equals(other.pathToUI))
			return false;
		if (vendor == null) {
			if (other.vendor != null)
				return false;
		} else if (!vendor.equals(other.vendor))
			return false;
		if (vendorURL == null) {
			if (other.vendorURL != null)
				return false;
		} else if (!vendorURL.equals(other.vendorURL))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}
