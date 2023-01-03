package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void delete(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(searchKey);
        }
    }

    public final void save(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (isExist(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            doSave(r, searchKey);
        }
    }

    public final Resume get(String uuid) {
        if (!isExist(getSearchKey(uuid))) {
            throw new NotExistStorageException(uuid);
        } else {
            return doGet(uuid);
        }
    }

    public final void update(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (isExist(searchKey)) {
            doUpdate(r, searchKey);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }


    protected abstract Object getSearchKey(String uuid);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract Resume doGet(String uuid);

    protected abstract boolean isExist(Object searchKey);
}
