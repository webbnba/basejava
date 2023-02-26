package com.urise.webapp.storage;

import com.urise.webapp.storage.stratege.XmlStreamSerializer;

import java.nio.file.Path;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(Path.of(String.valueOf(STORAGE_DIR)), new XmlStreamSerializer()));
    }
}
