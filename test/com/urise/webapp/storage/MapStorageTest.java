package com.urise.webapp.storage;

public class MapStorageTest extends AbstractArrayStorageTest{

    private static final Storage MAP_STORAGE = new MapStorage();


    public MapStorageTest(Storage storage) {
        super(MAP_STORAGE);
    }
}
