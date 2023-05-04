package com.urise.webapp.util;
import com.urise.webapp.model.AbstractSection;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.TextSection;
import org.junit.jupiter.api.Test;

import static com.urise.webapp.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {

    @Test
    public void readResume() {
        String json = JsonParser.write(R1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        assertEquals(R1, resume);
    }

    @Test
    public void write() {
        AbstractSection section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, AbstractSection.class);
        System.out.println(json);
        AbstractSection section2 = JsonParser.read(json, AbstractSection.class);
        assertEquals(section1, section2);
    }
}


