package com.puresol.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Storable {

    public StoreType getStoreType();

    public void store(File file) throws IOException, FileNotFoundException;

}
