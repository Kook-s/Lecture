package io.dev.lecture;

import io.dev.lecture.domain.model.Lecture;
import io.dev.lecture.domain.model.Schedule;
import io.dev.lecture.domain.repository.LectureRepository;
import io.dev.lecture.infra.Entity.LectureEntity;
import io.dev.lecture.infra.Entity.LectureScheduleEntity;
import io.dev.lecture.support.CustomException;
import io.dev.lecture.support.ErrorCode;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LectureRepositoryImpl - findById 테스트")
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class LectureRepositoryImplTest {

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    EntityManager em;

    @Test
    void 강의와_강의상세_조회_정상작동() {

        List<LectureEntity> resultList = em.createQuery("SELECT l FROM lecture  l", LectureEntity.class).getResultList();
        System.out.println("resultList = " + resultList);

        LectureEntity result = em.find(LectureEntity.class, 1L);
        Lecture lecture = lectureRepository.findById(1L)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        System.out.println("result = " + lecture.schedules());


        for (LectureScheduleEntity lectureSchedule : result.getLectureSchedules()) {
            System.out.println("lectureSchedule.id = " + lectureSchedule.getId());
            System.out.println("lectureSchedule.current = " + lectureSchedule.getCurrentCapacity());
            System.out.println("lectureSchedule.max = " + lectureSchedule.getMaxCapacity());

        }


    }
}
