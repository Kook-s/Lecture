package io.dev.lecture.domain.repository;

import io.dev.lecture.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> getByUserId(long id);
    List<User> findUserByIds(List<Long> userIds);
}
