package io.dev.lecture.infra.repository.impl;

import io.dev.lecture.domain.model.Registration;
import io.dev.lecture.domain.repository.RegistrationRepository;
import io.dev.lecture.infra.entity.RegistrationEntity;
import io.dev.lecture.infra.entity.ScheduleEntity;
import io.dev.lecture.infra.entity.UserEntity;
import io.dev.lecture.infra.repository.RegistrationJpaRepository;
import io.dev.lecture.infra.repository.ScheduleJpaRepository;
import io.dev.lecture.infra.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RegistrationRepositoryImpl implements RegistrationRepository {

    private final RegistrationJpaRepository registrationJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final ScheduleJpaRepository scheduleJpaRepository;

    @Override
    public List<Registration> findById(long userId) {

        return registrationJpaRepository.findByUserId(userId)
                .stream()
                .map(RegistrationEntity::toRegistration)
                .toList();
    }

    @Override
    public void save(long userId, long scheduleId) {

        UserEntity user = userJpaRepository.findById(userId).get();
        ScheduleEntity schedule = scheduleJpaRepository.findById(scheduleId).get();

        registrationJpaRepository.save(new RegistrationEntity(user, schedule));
    }

    @Override
    public boolean checkRegistration(long userId, long scheduleId) {
        return registrationJpaRepository.findByUserIdAndScheduleId(userId, scheduleId).isPresent();
    }
}
