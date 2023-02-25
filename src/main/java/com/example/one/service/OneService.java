package com.example.one.service;

import java.time.LocalDateTime;

public interface OneService {

    void addUser(String id, String password);

    String loginCheckUser(String id, String password);

    LocalDateTime enterMain(String token);
}
