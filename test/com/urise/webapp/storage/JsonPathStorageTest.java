package com.urise.webapp.storage;

import com.urise.webapp.storage.stratege.JsonStreamSerializer;

import java.nio.file.Path;

public class JsonPathStorageTest extends AbstractStorageTest{
    public JsonPathStorageTest() {
        super(new PathStorage(Path.of(String.valueOf(STORAGE_DIR)), new JsonStreamSerializer()));
    }
}
