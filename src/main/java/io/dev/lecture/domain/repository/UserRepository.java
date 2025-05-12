package io.dev.lecture.domain.repository;

import io.dev.lecture.domain.model.User;

import java.util.List;

public interface UserRepository {

    User getByUserid(long id);
    List<User> findUserByIds(List<Long> userIds);
}
