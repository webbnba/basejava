package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
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

    public final void save(Resume r) {
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


    public final void delete(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - 1);
            size--;
        } else {
            System.out.println("ERROR: This resume " + uuid + " not found");
        }
    }

    public final void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("ERROR: " + r + " not found");
        }
    }

    public final Resume get(String uuid) {
        if (getIndex(uuid) < 0) {
            System.out.println("ERROR: Resume " + uuid + " not found");
            return null;
        } else {
            return storage[getIndex(uuid)];
        }
    }


    protected abstract int getIndex(String uuid);

}