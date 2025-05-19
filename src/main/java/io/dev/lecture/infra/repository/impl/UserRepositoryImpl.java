package io.dev.lecture.infra.repository.impl;

import io.dev.lecture.domain.repository.UserRepository;
import io.dev.lecture.infra.entity.User;
import io.dev.lecture.infra.repository.UserJpaRepository;
import io.dev.lecture.support.CustomException;
import io.dev.lecture.support.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findById(long userId) {
        return userJpaRepository.findById(userId);
    }
}
