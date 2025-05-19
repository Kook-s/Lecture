package io.dev.lecture.application;

import io.dev.lecture.application.dto.RegistrationResponse;
import io.dev.lecture.application.dto.ScheduleResponse;
import io.dev.lecture.domain.model.Registration;
import io.dev.lecture.domain.model.Schedule;
import io.dev.lecture.domain.model.User;
import io.dev.lecture.domain.service.LectureService;
import io.dev.lecture.domain.service.RegistrationService;
import io.dev.lecture.domain.service.UserService;
import io.dev.lecture.infra.entity.ScheduleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureFacade {

    private final UserService userService;
    private final RegistrationService registrationService;
    private final LectureService lectureService;

    @Transactional
    public RegistrationResponse registrationLecture(long userId, long lectureId) {
        User user = userService.getUser(userId);
        Schedule lecture = lectureService.getLecture(lectureId);

        registrationService.register(user.id(), lecture.scheduleId());
        int currentCapacity = lectureService.increaseCapacity(lecture.scheduleId());

        return new RegistrationResponse(user.id(), lecture.scheduleId(), currentCapacity);
    }

    @Transactional
    public List<ScheduleResponse> AvailableLecture(LocalDateTime date) {
        List<Schedule> result = lectureService.getAvailableLectures(date);

        return result.stream()
                .map(s -> new ScheduleResponse(
                        s.scheduleId(),
                        s.lecture().title(),
                        s.maxCapacity(),
                        s.currentCapacity(),
                        s.startTime(),
                        s.endTime()
                ))
                .toList();
    }

    @Transactional
    public List<ScheduleResponse> getRegisteredLectures(long userId) {
        User user = userService.getUser(userId);
        List<Long> scheduleIds = registrationService.getRegistrations(userId)
                .stream()
                .map(Registration::scheduleId)
                .distinct()
                .toList();

        List<Schedule> result = lectureService.getAllLectures(scheduleIds);

        return result.stream()
                .map(s -> new ScheduleResponse(
                        s.scheduleId(),
                        s.lecture().title(),
                        s.maxCapacity(),
                        s.currentCapacity(),
                        s.startTime(),
                        s.endTime()
                ))
                .toList();
    }



}
