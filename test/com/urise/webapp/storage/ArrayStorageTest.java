package com.urise.webapp.storage;


class ArrayStorageTest extends AbstractArrayStorageTest {

    private final static Storage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public ArrayStorageTest() {
        super(SORTED_ARRAY_STORAGE);
    }
}