package io.dev.lecture.application;

import io.dev.lecture.application.dto.RegistrationResponse;
import io.dev.lecture.domain.model.Schedule;
import io.dev.lecture.domain.model.User;
import io.dev.lecture.domain.service.LectureService;
import io.dev.lecture.domain.service.RegistrationService;
import io.dev.lecture.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureFacade {

    private final UserService userService;
    private final RegistrationService registrationService;
    private final LectureService lectureService;

    public RegistrationResponse registrationLecture(long userId, long lectureId) {
        User user = userService.getUser(userId);
        Schedule lecture = lectureService.getLecture(lectureId);

        registrationService.register(user.id(), lecture.scheduleId());
        int currentCapacity = lectureService.increaseCapacity(lecture.scheduleId());

        return new RegistrationResponse(user.id(), lecture.scheduleId(), currentCapacity);
    }


}
