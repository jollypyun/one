package com.example.one.support.common;

import java.util.UUID;

public class GenerateId {
    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
