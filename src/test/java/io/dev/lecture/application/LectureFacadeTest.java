package io.dev.lecture.application;

import io.dev.lecture.application.LectureFacade;
import io.dev.lecture.domain.model.Lecture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class LectureFacadeTest {

    @Autowired
    private LectureFacade lectureFacade;

    @Test
    void 강의_신청이_정상적으로_이뤄져야_한다() {
        long userId = 1L;
        long lectureId = 2L;
        long scheduleId = 1L;

        lectureFacade.registerLecture(userId, lectureId, scheduleId);

        List<Lecture> lectures = lectureFacade.getRegistrationLecture(userId);

        System.out.println("✅ 신청한 유저 ID: " + userId);
        System.out.println("📦 신청된 강의 목록:");
        lectures.forEach(l -> System.out.println(" - " + l));

        assertFalse(lectures.isEmpty());
        assertTrue(lectures.stream().anyMatch(l -> l.lectureId().equals(lectureId)));
    }

    @Test
    void 수강_가능한_강의_목록을_조회한다() {
        LocalDateTime 기준시간 = LocalDateTime.of(2025, 5, 9, 12, 0);

        List<Lecture> lectures = lectureFacade.getAvailableLectures(기준시간);

        System.out.println("📅 기준 시간 이후 수강 가능 강의:");
        lectures.forEach(l -> System.out.println(" - " + l));

        assertFalse(lectures.isEmpty());
    }

    @Test
    void 유저가_신청한_강의목록을_조회한다() {
        long userId = 1L;

        List<Lecture> lectures = lectureFacade.getRegistrationLecture(userId);

        System.out.println("👤 유저 ID: " + userId + " 가 신청한 강의 목록:");
        lectures.forEach(l -> System.out.println(" - " + l));

        assertFalse(lectures.isEmpty());
    }
}
