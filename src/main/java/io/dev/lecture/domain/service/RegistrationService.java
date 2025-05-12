package io.dev.lecture.domain.service;

import io.dev.lecture.domain.model.Registration;
import io.dev.lecture.domain.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public Registration getRegistration (long registrationId) {
        return registrationRepository.findByRegistration(registrationId).orElseThrow();
    }

    public void saveRegistration (long userId, long lectureId) {
        registrationRepository.save(userId, lectureId);
    }
}
