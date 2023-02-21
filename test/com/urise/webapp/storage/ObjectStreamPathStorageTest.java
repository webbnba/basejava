package com.urise.webapp.storage;

import java.nio.file.Path;

public class ObjectStreamPathStorageTest extends AbstractStorageTest{

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(Path.of(String.valueOf(STORAGE_DIR)), new ObjectStreamStorage()));
    }
}
