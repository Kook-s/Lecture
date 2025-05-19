package io.dev.lecture.domain.repository;

import io.dev.lecture.infra.entity.User;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(long userId);
}
