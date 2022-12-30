package com.urise.webapp.storage;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {
    private final static Storage ARRAY_STORAGE = new SortedArrayStorage();

    public SortedArrayStorageTest() {
        super(ARRAY_STORAGE);
    }
}