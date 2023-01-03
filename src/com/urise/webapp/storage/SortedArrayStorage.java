package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void doSave(Resume r, Object searchKey) {
        int index = (int) searchKey;
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
    protected void doDelete(Object searchKey) {
        int index = (int) searchKey;
        System.arraycopy(storage, index + 1, storage, index, size - 1);
        size--;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage[(int) searchKey] = r;
    }

    @Override
    protected Resume doGet(String uuid) {
        return storage[(int) getSearchKey(uuid)];
    }

    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}