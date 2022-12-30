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
    protected void updateResume(Resume r, int index) {
        resumeList.set(index, r);
    }

    @Override
    protected Resume getResume(String uuid) {
        int index = resumeList.indexOf(new Resume(uuid));
        return resumeList.get(index);
    }

    @Override
    public void deleteResume(int index) {
        resumeList.remove(index);
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
    protected int getIndex(String uuid) {
        return resumeList.indexOf(new Resume(uuid));
    }

    @Override
    protected void saveResume(Resume r, int index) {
        resumeList.add(r);
    }
}
