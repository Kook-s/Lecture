package io.dev.lecture.domain.repository;

import io.dev.lecture.domain.model.Registration;

import java.util.Optional;

public interface RegistrationRepository {

    Optional<Registration> findByRegistration(long registrationId);
    void save(long userId, long lectureId);
}
