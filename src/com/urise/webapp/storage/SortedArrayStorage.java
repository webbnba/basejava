package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void saveResume(Resume r) {
        while (true) {
            if (size >= STORAGE_LIMIT) {
                System.out.println("Storage size is fool");
            }
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

    @Override
    protected void deleteResume(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - 1);
            size--;
        } else {
            System.out.println("ERROR: This resume " + uuid + " not found");
        }
    }


    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}