package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private static final Map<String, Resume> resumeMap = new HashMap<>();

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
        Resume[] resumes = new Resume[0];
        for (Map.Entry<String, Resume> entry : resumeMap.entrySet()) {
            List<Resume> resumeList = new ArrayList<>();
            resumeList.add(entry.getValue());
            resumes = resumeList.toArray(new Resume[0]);
        }
        return resumes;
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
