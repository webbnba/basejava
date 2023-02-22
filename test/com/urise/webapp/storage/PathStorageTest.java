package com.urise.webapp.storage;

import com.urise.webapp.storage.stratege.ObjectStreamStorage;

import java.nio.file.Path;

public class PathStorageTest extends AbstractStorageTest{

    public PathStorageTest() {
        super(new PathStorage(Path.of(String.valueOf(STORAGE_DIR)), new ObjectStreamStorage()));
    }
}
