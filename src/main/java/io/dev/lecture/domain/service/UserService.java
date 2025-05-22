package io.dev.lecture.domain.service;

import io.dev.lecture.domain.repository.UserRepository;
import io.dev.lecture.infra.entity.User;
import io.dev.lecture.support.CustomException;
import io.dev.lecture.support.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
