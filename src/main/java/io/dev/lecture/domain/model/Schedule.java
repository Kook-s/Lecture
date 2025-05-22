package io.dev.lecture.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record Schedule(
        Long scheduleId,
        int maxCapacity,
        int currentCapacity,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime startTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime endTime,
        Lecture lecture
) {
}
