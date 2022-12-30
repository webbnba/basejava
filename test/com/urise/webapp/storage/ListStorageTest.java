package com.urise.webapp.storage;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ListStorageTest extends AbstractArrayStorageTest {
    private final static Storage LIST_STORAGE = new ListStorage();

    public ListStorageTest() {
        super(LIST_STORAGE);
    }

    @Disabled
    @Test
    @Override
    void saveOverflow() {
    }
}
