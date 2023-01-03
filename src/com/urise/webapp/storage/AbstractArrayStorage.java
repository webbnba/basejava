package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }


    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }
    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

}