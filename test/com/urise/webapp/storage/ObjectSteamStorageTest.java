package com.urise.webapp.storage;

import com.urise.webapp.storage.stratege.ObjectStreamStorage;

public class ObjectSteamStorageTest extends AbstractStorageTest {

    public ObjectSteamStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStorage()));
    }

}
