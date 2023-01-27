package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public abstract class AbstractStorageTest {
    protected final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1 = new Resume(UUID_1, "fullName1");
    private static final Resume RESUME_2 = new Resume(UUID_2, "fullName2");
    private static final Resume RESUME_3 = new Resume(UUID_3, "fullName3");

    private static final Resume RESUME_4 = new Resume(UUID_4, "fullName4");

    private static final String UUID_NOT_EXIST = "dummy";

    Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};


    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void size() {
        assertSize(3);
    }

    private void assertSize(int countResume) {
         assertEquals(countResume, storage.size());
    }

    @Test
    void getAll() {
        assertArrayEquals(expected, storage.getAllSorted().toArray());
        assertSize(3);
    }


    @Test
    void clear() {
        storage.clear();
        assertSize(0);
        assertArrayEquals(new Storage[0], storage.getAllSorted().toArray());
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    void saveIfExist() throws ExistStorageException {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME_3));
    }

    @Test
    void delete() throws NotExistStorageException {
        storage.delete(RESUME_2.getUuid());
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> storage.delete(RESUME_2.getUuid()));
    }

    @Test
    void update() {
        storage.update(RESUME_2);
        assertSame(RESUME_2, storage.get(RESUME_2.getUuid()));
    }

    @Test
    void updateIfNotExistResume() throws NotExistStorageException {
        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume(UUID_NOT_EXIST, "fullName")));
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    void getNotExist() throws NotExistStorageException {
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_NOT_EXIST));
    }
}