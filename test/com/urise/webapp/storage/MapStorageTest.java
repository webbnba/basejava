package com.urise.webapp.storage;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class MapStorageTest extends AbstractArrayStorageTest{

    private static final Storage MAP_STORAGE = new MapStorage();


    public MapStorageTest() {
        super(MAP_STORAGE);
    }
    @Disabled
    @Test
    @Override
    void saveOverflow() {
    }
}
