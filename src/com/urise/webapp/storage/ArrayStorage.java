package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("ERROR: " + r + " is present");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage size is fool");
        } else {
            storage[size] = r;
            size++;
        }
    }

    @Override
    protected void deleteResume(String uuid) {
        if (getIndex(uuid) != -1) {
            storage[getIndex(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: This resume " + uuid + " not found");
        }
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