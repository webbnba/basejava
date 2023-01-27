package com.urise.webapp.model;

import java.util.List;

public class ListSection extends AbstractSection{
    private List<String> sections;

    public ListSection(List<String> sections) {
        this.sections = sections;
    }

    public List<String> getSections() {
        return sections;
    }


}
