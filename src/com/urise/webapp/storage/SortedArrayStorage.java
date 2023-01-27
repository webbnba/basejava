package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);


    @Override
    protected void doSave(Resume r, Integer searchKey) {
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
    protected void doDelete(Integer searchKey) {
        int index = (int) searchKey;
        System.arraycopy(storage, index + 1, storage, index, size - 1);
        size--;
    }

    @Override
    protected void doUpdate(Resume r, Integer searchKey) {
        storage[searchKey] = r;
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "fullName");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }
}