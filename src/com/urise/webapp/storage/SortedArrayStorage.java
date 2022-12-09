package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        while (true) {
            int index = getIndex(r.getUuid());
            if (index >= 0) {
                System.out.println("ERROR: " + r + " is present");
                break;
            }

            index = -index - 1;
            if (index < size) {
                System.arraycopy(storage, index, storage, index + 1, size - index);

            }
            storage[index] = r;
            size++;
            break;
        }
    }


    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - 1);
            size--;
        } else {
            System.out.println("ERROR: This resume " + uuid + " not found");
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("ERROR: " + r + " not found");
        }
    }


    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("ERROR: Resume " + uuid + " not found");
            return null;
        }
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}