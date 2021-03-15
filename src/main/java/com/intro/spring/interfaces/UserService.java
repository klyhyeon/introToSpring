package com.intro.spring.interfaces;

public interface UserService {
    //사용자 정보를 등록한다.
    void register(User user, String rawPassword);
}
