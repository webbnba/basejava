package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
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
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage size is fool ", r.getUuid());
        } else {
            saveResume(r, index);
            size++;
        }
    }


    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(index);
            size--;
        }
    }

    public final void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    public final Resume get(String uuid) {
        if (getIndex(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return storage[getIndex(uuid)];
        }
    }


    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(Resume r, int index);


    protected abstract void deleteResume(int index);

}