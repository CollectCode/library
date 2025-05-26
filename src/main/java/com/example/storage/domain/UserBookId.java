package com.example.storage.domain;

import java.io.Serializable;
import java.util.Objects;

public class UserBookId implements Serializable {

    private String user;
    private Integer book;

    public UserBookId() { }

    public UserBookId(String userId, Integer bookId) {
        this.user = userId;
        this.book = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserBookId)) return false;
        UserBookId that = (UserBookId) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, book);
    }
}
