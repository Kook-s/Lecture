package io.dev.lecture.domain.service;

import io.dev.lecture.domain.model.Registration;
import io.dev.lecture.domain.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public List<Registration> getRegistration (long userId) {
        return registrationRepository.findByRegistration(userId);
    }

    public void saveRegistration (long userId, long lectureId) {
        registrationRepository.save(userId, lectureId);
    }
}
