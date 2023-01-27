package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private static final List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    protected void doUpdate(Resume r, Integer searchKey) {
        resumeList.set(searchKey, r);
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return resumeList.get(searchKey);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return resumeList;
    }

    @Override
    public void doDelete(Integer searchKey) {

        resumeList.remove((int) searchKey);
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for(int i = 0; i < resumeList.size(); i++) {
            if(resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void doSave(Resume r, Integer searchKey) {
        resumeList.add(r);
    }
}
