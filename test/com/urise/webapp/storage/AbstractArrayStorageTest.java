package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }

    @Test
    void getAll() {
        Resume[] expected = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        assertArrayEquals(expected, storage.getAll());
    }

    @Test
    void clear() {
        storage.clear();
        int actual = storage.size();
        assertEquals(0, actual);
    }

    @Test
    void save() {
        String UUID_4 = "uuid4";
        Resume[] expected = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3), new Resume(UUID_4)};
        storage.save(new Resume(UUID_4));
        assertArrayEquals(expected, storage.getAll());
    }

    @Test
    void saveIfExist() throws ExistStorageException {
        assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    void saveResumeIfStorageFool() throws StorageException {
        storage.clear();
        for (int i = 0; i < 10000; i++) {
            storage.save(new Resume("UUID_" + i));
        }
        assertThrows(StorageException.class, () -> storage.save(new Resume("dummy")));
    }

    @Test
    void delete() {
        Resume[] expected = {new Resume(UUID_1), new Resume(UUID_3)};
        storage.delete("uuid2");
        assertArrayEquals(expected, storage.getAll());
    }

    @Test
    void deleteIfNotExist() throws NotExistStorageException {
        assertThrows(NotExistStorageException.class, () -> storage.delete("dummy"));
    }

    @Test
    void update() {
        Resume[] expected = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        storage.update(new Resume(UUID_2));
        assertArrayEquals(expected, storage.getAll());
    }

    @Test
    void updateIfNotExistResume() throws NotExistStorageException {
        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("dummy")));
    }

    @Test
    void get() {
        Resume actual = storage.get(UUID_2);
        assertEquals(new Resume(UUID_2), actual);
    }

    @Test
    void getNotExist() throws NotExistStorageException {
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }
}