package com.urise.webapp.storage;

import static org.junit.jupiter.api.Assertions.*;

class SortedArrayStorageTest extends AbstractArrayStorageTest {
    private final static Storage ARRAY_STORAGE = new SortedArrayStorage();

    public SortedArrayStorageTest() {
        super(ARRAY_STORAGE);
    }
}