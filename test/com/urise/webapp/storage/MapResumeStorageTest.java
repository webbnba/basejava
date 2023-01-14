package com.urise.webapp.storage;

public class MapResumeStorageTest extends AbstractStorageTest {
    private static final Storage MAP_RESUME_STORAGE = new MapUuidStorage();

    public MapResumeStorageTest() {
        super(MAP_RESUME_STORAGE);
    }
}
