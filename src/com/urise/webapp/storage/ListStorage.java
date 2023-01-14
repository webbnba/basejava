package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListStorage extends AbstractStorage {

    private static final List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return resumeList.stream().sorted(Comparator.comparing(Resume::getFullName)).collect(Collectors.toList());
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        resumeList.set((Integer) searchKey, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return resumeList.get((int) searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    public void doDelete(Object searchKey) {

        resumeList.remove((int) searchKey);
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for(int i = 0; i < resumeList.size(); i++) {
            if(resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        resumeList.add(r);
    }
}
