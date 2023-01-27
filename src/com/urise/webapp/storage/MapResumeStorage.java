package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private static final Map<String, Resume> mapResumeStorage = new LinkedHashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return mapResumeStorage.get(uuid);
    }

    @Override
    protected void doSave(Resume r, Resume resume) {
        mapResumeStorage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Resume resume) {
        mapResumeStorage.remove(resume.getUuid());
    }

    @Override
    protected void doUpdate(Resume r, Resume resume) {
        mapResumeStorage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Resume resume) {
        return mapResumeStorage.get(resume.getUuid());
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(mapResumeStorage.values());
    }

    @Override
    public void clear() {
        mapResumeStorage.clear();
    }

    @Override
    public int size() {
        return mapResumeStorage.size();
    }
}
