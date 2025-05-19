package io.dev.lecture.domain.repository;

import io.dev.lecture.domain.model.Registration;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository {

    List<Registration> findById(long userId);
    void save(long userId, long scheduleId);
}
