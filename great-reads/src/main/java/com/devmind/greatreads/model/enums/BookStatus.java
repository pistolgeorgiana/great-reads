package com.devmind.greatreads.model.enums;

import com.devmind.greatreads.exceptions.RecordNotFoundException;

public enum BookStatus {

    APPROVED, REJECTED, PENDING;

    public static BookStatus value(String status) {
        for (BookStatus type : values()) {
            if (type.name().equals(status)) {
                return type;
            }
        }
        throw new RecordNotFoundException("The status " + status + " cannot be found!");
    }
}
