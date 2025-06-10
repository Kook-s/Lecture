package io.dev.lecture.application.dto;

public record RegistrationResponse(
    long userId,
    long scheduleId,
    int currentCapacity
){
}
