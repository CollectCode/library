package com.example.storage.domain;

import java.io.Serializable;
import java.util.Objects;

public class UserBookId implements Serializable {

    private String userId;
    private Integer bookId;

    public UserBookId() { }

    public UserBookId(String userId, Integer bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserBookId)) return false;
        UserBookId that = (UserBookId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, bookId);
    }
}
