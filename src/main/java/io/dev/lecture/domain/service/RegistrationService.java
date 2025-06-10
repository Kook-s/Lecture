package io.dev.lecture.domain.service;

import io.dev.lecture.domain.model.Registration;
import io.dev.lecture.domain.repository.RegistrationRepository;
import io.dev.lecture.support.CustomException;
import io.dev.lecture.support.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletionException;

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

    public void checkRegistration(long userId, long scheduleId) {
        if (registrationRepository.checkRegistration(userId, scheduleId)) {
            throw new CustomException(ErrorCode.LECTURE_ALREADY_REGISTRATION);
        }
    }
}
