package com.urise.webapp.storage;

import com.urise.webapp.storage.stratege.DataStreamSerializer;

import java.nio.file.Path;

public class DataPathStorageTest extends AbstractStorageTest{
    public DataPathStorageTest() {
        super(new PathStorage(Path.of(String.valueOf(STORAGE_DIR)), new DataStreamSerializer()));
    }
}
