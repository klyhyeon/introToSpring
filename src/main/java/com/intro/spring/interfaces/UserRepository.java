package com.intro.spring.interfaces;

public interface UserRepository {
    //사용자 정보를 저장한다.
    User save(User user);
    //사용자 계정명이 일치하는 사용자 수를 카운트한다.
    int countByUserName(String username);
}
