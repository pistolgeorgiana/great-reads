package com.devmind.greatreads.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ADMIN(Roles.ADMIN),
    AUTHOR(Roles.AUTHOR),
    READER(Roles.READER);

    private final String role;

    public static class Roles {
        public static final String ADMIN = "Admin";
        public static final String AUTHOR = "Author";
        public static final String READER = "Reader";
    }
}
