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
       saveResume(r);
    }


    public final void delete(String uuid) {
       deleteResume(uuid);
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

    protected abstract void saveResume(Resume r);

    protected abstract void deleteResume(String uuid);

}