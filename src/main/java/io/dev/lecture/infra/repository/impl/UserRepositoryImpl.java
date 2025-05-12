package io.dev.lecture.infra.repository.impl;

import io.dev.lecture.domain.model.User;
import io.dev.lecture.domain.repository.UserRepository;
import io.dev.lecture.infra.Entity.UserEntity;
import io.dev.lecture.infra.repository.UserJpaRepository;
import io.dev.lecture.support.CustomException;
import io.dev.lecture.support.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> getByUserId(long id) {
        return userJpaRepository.findById(id)
                .map(UserEntity::toUser);
    }

    @Override
    public List<User> findUserByIds(List<Long> userIds) {
        return userJpaRepository.findAllById(userIds)
                .stream()
                .map(UserEntity::toUser)
                .toList();
    }
}
