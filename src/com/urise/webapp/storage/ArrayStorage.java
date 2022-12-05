package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        if (isPresent(r.getUuid())) {
            r.setUuid("uuidUpdate");
        } else System.out.println("ERROR: " + r + " not found");
    }


    public void save(Resume r) {
        if (!isPresent(r.getUuid())) {
            if (storage.length > size) {
                storage[size] = r;
                size++;
            } else System.out.println("Storage size is fool");
        } else System.out.println("ERROR: " + r + " is present");
    }

    public Resume get(String uuid) {
        if (isPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(uuid)) {
                    return storage[i];
                }
            }
        } else {
            System.out.println("ERROR: Resume " + uuid + " not found");
        }
        return null;

    }

    public void delete(String uuid) {
        if (isPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                }
            }
        } else System.out.println("ERROR: This resume " + uuid + " not found");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private boolean isPresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return true;
            }
        }
        return false;
    }
}