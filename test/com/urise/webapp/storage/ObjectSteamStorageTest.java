package com.urise.webapp.storage;

public class ObjectSteamStorageTest extends AbstractStorageTest {

    public ObjectSteamStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStorage()));
    }

}
