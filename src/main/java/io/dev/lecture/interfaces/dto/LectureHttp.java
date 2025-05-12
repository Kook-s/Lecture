package io.dev.lecture.interfaces.dto;

import io.dev.lecture.domain.model.Lecture;
import io.dev.lecture.domain.model.Registration;

import java.time.LocalDateTime;
import java.util.List;

public class LectureHttp {

    public static class RegistrationLecture {

        public record Request(
                Long lectureId,
                Long lectureScheduleId,
                Long userId
        ) {}
        public record Response(
                Registration registration
        ) {}
    }

    public static class AvailableLectures {
        public record Request(
                LocalDateTime date
        ) {}

        public record Response(
                List<Lecture> registrationList
        ) {}
    }

    public static class RegistrationUserLecture {
        public record Request(
                long userId
        ){}

        public record Response(
                List<Lecture> lectureList
        ) {}
    }
}


