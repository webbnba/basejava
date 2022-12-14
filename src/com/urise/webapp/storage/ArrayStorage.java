package com.urise.webapp.storage;


import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void deleteResume(String uuid, int index) {

        storage[index] = storage[size - 1];
        storage[size - 1] = null;

    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}