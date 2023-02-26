package com.urise.webapp.storage;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses(
                {ArrayStorageTest.class,
                SortedArrayStorageTest.class,
                ListStorageTest.class,
                MapResumeStorageTest.class,
                MapUuidStorageTest.class,
                ObjectSteamStorageTest.class,
                PathStorageTest.class,
                DataPathStorageTest.class,
                JsonPathStorageTest.class,
                XmlPathStorageTest.class})

public class AllStorageTest {
}
