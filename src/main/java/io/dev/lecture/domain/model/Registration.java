package io.dev.lecture.domain.model;

import lombok.Builder;

@Builder
public record Registration(
        Long registrationId,
        Long userId,
        Long lectureId
) {
}
