package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    private static final Map<String, Resume> mapResumeStorage = new LinkedHashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return mapResumeStorage.get(uuid);
    }

    @Override
    protected void doSave(Resume r, Object resume) {
       mapResumeStorage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object resume) {
        mapResumeStorage.remove(((Resume) resume).getUuid());
    }

    @Override
    protected void doUpdate(Resume r, Object resume) {
       mapResumeStorage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object resume) {
        return mapResumeStorage.get(((Resume) resume).getUuid());
    }

    @Override
    protected boolean isExist(Object resume) {
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
