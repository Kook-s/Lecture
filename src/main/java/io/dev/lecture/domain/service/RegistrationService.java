package io.dev.lecture.domain.service;

import io.dev.lecture.domain.model.Registration;
import io.dev.lecture.domain.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public List<Registration> getRegistrations(long id) {
        return registrationRepository.findById(id);
    }

    public void register(long userId, long scheduleId) {
        registrationRepository.save(userId, scheduleId);
    }
}
