package com.urise.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Телефон"),
    SKYPE("Скайп"),
    EMAIL("Электронная почта"),
    ACCOUNT_LINKED_IN("Аккаунт LinkedIn"),
    ACCOUNT_GIT_HUB("Аккаунт GitHub"),
    ACCOUNT_STACK_OVERFLOW("Аккаунт StackOverflow"),
    HOME_PAGE("Домашняя страница");

    public final String title;
    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
