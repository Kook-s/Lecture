package io.dev.lecture.application.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

public record ScheduleResponse(
        Long scheduleId,
        String title,
        int maxCapacity,
        int currentCapacity,
        LocalDateTime startAt,
        LocalDateTime endAt
) {
}
