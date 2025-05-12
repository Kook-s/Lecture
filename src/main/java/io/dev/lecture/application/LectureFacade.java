package io.dev.lecture.application;

import io.dev.lecture.domain.model.Lecture;
import io.dev.lecture.domain.model.Registration;
import io.dev.lecture.domain.model.User;
import io.dev.lecture.domain.service.LectureService;
import io.dev.lecture.domain.service.RegistrationService;
import io.dev.lecture.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureFacade {

    private final UserService userService;
    private final LectureService lectureService;
    private final RegistrationService registrationService;

    @Transactional
    public void registerLecture(long userId, long lectureId, long scheduleId) {
        User user = userService.getUserId(userId);

        Lecture lecture = lectureService.getAvailableLecture(lectureId);

        registrationService.saveRegistration(user.userId(), lecture.lectureId());

        lectureService.insertLecture(scheduleId);
    }

    @Transactional(readOnly = true)
    public List<Lecture> getAvailableLectures(LocalDateTime date) {
        return lectureService.getAvailableTimeLecture(date);
    }


    @Transactional(readOnly = true)
    public List<Lecture> getRegistrationLecture(long userId) {

        User user = userService.getUserId(userId);
        List<Registration> registration = registrationService.getRegistration(user.userId());

        List<Long> lectureIds = registration.stream()
                .map(Registration::lectureId)
                .distinct()
                .toList();

        return lectureService.getLectureByIds(lectureIds);
    }


}
