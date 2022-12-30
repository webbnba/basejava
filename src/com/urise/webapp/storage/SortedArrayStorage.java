package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void saveResume(Resume r, int index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage size is fool ", r.getUuid());
        }
        index = -index - 1;
        if (index < size) {
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        storage[index] = r;
        size++;
    }

    @Override
    protected void deleteResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1);
        size--;
    }

    @Override
    protected void updateResume(Resume r, int index) {
        storage[index] = r;
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage[getIndex(uuid)];
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}