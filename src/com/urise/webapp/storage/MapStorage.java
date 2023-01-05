package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private static final Map<String, Resume> resumeMap = new LinkedHashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        resumeMap.put((String) searchKey, r);

    }

    @Override
    protected void doDelete(Object searchKey) {
        resumeMap.remove((String) searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        resumeMap.put((String) searchKey, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return resumeMap.get((String) searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return resumeMap.containsKey((String) searchKey);
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public Resume[] getAll() {
        return resumeMap.values().toArray(new Resume[0]) ;
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
