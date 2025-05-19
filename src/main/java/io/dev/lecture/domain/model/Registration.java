package io.dev.lecture.domain.model;

public record Registration(
        Long RegistrationId,
        Long userId,
        Long scheduleId
) {
}
