package io.dev.lecture.infra.repository.impl;

import io.dev.lecture.domain.model.Registration;
import io.dev.lecture.domain.repository.RegistrationRepository;
import io.dev.lecture.infra.Entity.LectureEntity;
import io.dev.lecture.infra.Entity.RegistrationEntity;
import io.dev.lecture.infra.Entity.UserEntity;
import io.dev.lecture.infra.repository.LectureJpaRepository;
import io.dev.lecture.infra.repository.RegistrationJpaRepository;
import io.dev.lecture.infra.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RegistrationRepositoryImpl implements RegistrationRepository {

    private final RegistrationJpaRepository registrationJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final LectureJpaRepository lectureJpaRepository;

    @Override
    public Optional<Registration> findByRegistration(long registrationId) {
        return registrationJpaRepository.findById(registrationId)
                .map(RegistrationEntity::toRegistration);
    }

    @Override
    public void save(long userId, long lectureId) {
        UserEntity user = userJpaRepository.findById(userId).get();
        LectureEntity lecture = lectureJpaRepository.findById(lectureId).get();

        registrationJpaRepository.save(new RegistrationEntity(user, lecture));
    }
}
