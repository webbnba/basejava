package com.urise.webapp.model;

public enum ContactsType {
    PHONE_NUMBER("Телефон"),
    SKYPE("Скайп"),
    EMAIL("Электронная почта"),
    ACCOUNT_LINKED_IN("Аккаунт LinkedIn"),
    ACCOUNT_GIT_HUB("Аккаунт GitHub"),
    ACCOUNT_STACK_OVERFLOW("Аккаунт StackOverflow"),
    HOME_PAGE("Домашняя страница");

    public String title;
    ContactsType(String title) {
        this.title = title;
    }
}
