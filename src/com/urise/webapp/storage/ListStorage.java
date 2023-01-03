package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private static final List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        resumeList.set((Integer) searchKey, r);
    }

    @Override
    protected Resume doGet(String uuid) {
        int index = resumeList.indexOf(new Resume(uuid));
        return resumeList.get(index);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    public void doDelete(Object searchKey) {

        resumeList.remove((int) searchKey);
    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return resumeList.indexOf(new Resume(uuid));
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        resumeList.add(r);
    }
}
