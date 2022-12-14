package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void saveResume(Resume r, int index) {
        index = -index - 1;
        if (index < size) {
            System.arraycopy(storage, index, storage, index + 1, size - index);

        }
        storage[index] = r;

    }


    @Override
    protected void deleteResume(String uuid, int index) {

        System.arraycopy(storage, index + 1, storage, index, size - 1);

    }


    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}