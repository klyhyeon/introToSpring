package com.intro.spring.interfaces;

public interface PasswordEncoder {
    //패스워드를 해시화한다.
    String encode(CharSequence rawPassword);
}
