package com.puresol.license.creator.store;

import java.security.KeyPair;
import java.util.List;

import com.puresol.license.api.Licensee;
import com.puresol.license.creator.exception.LicenseStoreException;

/**
 * This is the central interface for a license store. A license store is used to
 * handle licensees and licenser and the licenses provided between them.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface LicenseStore {

    /**
     * This method returns a list of all licensees currently in the store.
     * 
     * @return A {@link List} object is returned containing all licensees.
     */
    public List<Licensee> getLicensees();

    /**
     * This method adds a new licensee to the store.
     * 
     * @param licensee
     * @throws LicenseStoreException
     *             exception is thrown if the licensee could not be added.
     */
    public void addLicensee(Licensee licensee, KeyPair keyPair)
	    throws LicenseStoreException;

    /**
     * This method returns the associated key pair for the given
     * {@link Licensee}.
     * 
     * @param licensee
     * @return
     * @throws LicenseStoreException
     *             if the key pair could not be retrieved.
     */
    public KeyPair getKeyPair(Licensee licensee) throws LicenseStoreException;
}
