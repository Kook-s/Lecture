package io.dev.lecture.infra.repository.impl;

import io.dev.lecture.domain.model.User;
import io.dev.lecture.domain.repository.UserRepository;
import io.dev.lecture.infra.Entity.UserEntity;
import io.dev.lecture.infra.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User getByUserid(long id) {
        return userJpaRepository.findById(id)
                .map(UserEntity::toUser)
                .get();
    }

    @Override
    public List<User> findUserByIds(List<Long> userIds) {
        return List.of();
    }
}
