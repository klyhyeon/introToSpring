package com.intro.spring.service;

import com.intro.spring.interfaces.PasswordEncoder;
import com.intro.spring.interfaces.UserRepository;
import com.intro.spring.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.beans.ConstructorProperties;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(DataSource dataSource) {
        //데이터베이스 방식으로 사용자 정보를 관리하는 구현 클래스 => 결합도 높음
        this.userRepository = new JdbcUserRepository(dataSource);

        //Bcrypt 알고리즘으로 해시화하는 구현 클래스 => 결합도 높음
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    //결합도를 낮추면 더미 클래스로 개발 중단없이 계속할 수 있다.
    UserRepository userRepository = new DummyUserRepository();
    PasswordEncoder passwordEncoder = new DummyPasswordEncoder();
    UserService userService = new UserServiceImpl(userRepository, passwordEncoder);
    //생성자를 활용한 의존 컴포넌트 초기화
    //Autowired가 있으면 의존성 주입이 필요한 경우 DI컨테이너에서 자동으로 찾아 주입함
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void register(User user, String rawPassword) {
        if (this.userRepository.countByUserName(user.getusername()) > 0) {
            //같은 계정의 사용자가 있다면 예외를 발생시킨다.
            throw new UserAlreadyRegisteredException();
        }

        //입력된 원본 패스워드를 해시화한 후, 사용자 정보로 설정한다.
        user.setPassword(this.passwordEncoder.encode(rawPassword));
        this.userRepository.save(user);
    }

    //컨스트럭터 인젝션을 애너테이션 기반 설정으로 표현한 예
    @ConstructorProperties({"userRepository", "passwordEncoder"})
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        //...

    }

    //필수 조건을 완화해서 필드 인젝션을 한 예
    @Autowired(required = false)
    PasswordEncoder passwordEncoder;

    //스프링 4부터는 Optional을 사용할 수 있다.
    @Autowired
    Optional<PasswordEncoder> passwordEncoder;

    public void createUser(User user, String rawPassword) {
        String encodedPassword = passwordEncoder.map(x -> x.encode(rawPassword))
                                .orElse(rawPassword);
        //...
    }





}
