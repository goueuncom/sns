package com.fastcampus.sns.service;

import com.fastcampus.sns.exception.ErrorCode;
import com.fastcampus.sns.exception.SnsApplicationException;
import com.fastcampus.sns.model.User;
import com.fastcampus.sns.model.entity.UserEntity;
import com.fastcampus.sns.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserEntityRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public User join(String userName, String password) {
        userRepository.findByUserName(userName).isPresent(it -> {
            throw new SnsApplicationException();
        });

        userRepository.save(new UserEntity());

        return new User();
    }
    /*
    @Transactional
    public User join(String userName, String password) {
        System.out.println("step 1 join userName = " + userName);
        // 회원가입하려는 userName으로 회원가입된 user가 있는지
        userRepository.findByUserName(userName).ifPresent(it -> {
            System.out.println("step 2 join password = " + password);
            throw new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s is duplicated", userName));
        });

        // 회원가입 진행 = user를 등록
        UserEntity savedUser = userRepository.save(UserEntity.of(userName, encoder.encode(password)));

        // 의도적으로 throw Exception
        //throw new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s is duplicated", userName));
        //throw new RuntimeException();
        return User.fromEntity(savedUser);
    }
     */

    // TODO : implement
    public String login(String userName, String password) {
        // 회원가입 여부 체크
        UserEntity userEntity = userRepository.findByUserName(userName).orElseThrow(() -> new SnsApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", userName)));

        // 비밀번호 체크
        if (encoder.matches(password, userEntity.getPassword())) {
            //if (!userEntity.getPassword().equals(password)) {
            throw new SnsApplicationException(ErrorCode.INVALID_PASSWORD);
        }

        // 토큰 생성


        return "";
    }
}
