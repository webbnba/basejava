package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {

    private static final Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doSave(Resume r, String searchKey) {
        resumeMap.put(searchKey, r);

    }

    @Override
    protected void doDelete(String searchKey) {
        resumeMap.remove(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, String searchKey) {
        resumeMap.put(searchKey, r);
    }

    @Override
    protected Resume doGet(String searchKey) {
        return resumeMap.get(searchKey);
    }

    @Override
    protected boolean isExist(String searchKey) {
        return resumeMap.containsKey(searchKey);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(resumeMap.values());

    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
