package io.dev.lecture;

import io.dev.lecture.application.LectureFacade;
import io.dev.lecture.domain.model.Schedule;
import io.dev.lecture.domain.repository.LectureRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ConcurrentTest {

    @Autowired
    private LectureFacade lectureFacade;

    @Autowired
    private LectureRepository lectureRepository;

    @Test
    public void 동시에_40명이_지원_했을때_30명이_성공() throws Exception {
        //given
        Long scheduleId = 1L;

        //when
        int threadCount = 40;
        ExecutorService exService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 1; i <= threadCount; i++) {
            int finalI = i;
            exService.submit(() -> {
                try {
                lectureFacade.registrationLecture(finalI, 1);

                }catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }

            });
        }

        //then
        Schedule schedule = lectureRepository.findById(scheduleId).get();
        System.out.println("Test111 " +schedule);
        assertThat(schedule.currentCapacity()).isEqualTo(30);
    }

    @Test
    public void 동일한학생이여러번신청() throws Exception {
        //given
        Long scheduleId = 1L;

        //when
        int threadCount = 5;
        ExecutorService exService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 1; i <= threadCount; i++) {
            int finalI = i;
            exService.submit(() -> {
                try {
                    lectureFacade.registrationLecture(1, finalI);

                }catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }

            });
        }

        //then
        Schedule schedule = lectureRepository.findById(scheduleId).get();
        System.out.println("Test111 " +schedule);
        assertThat(schedule.currentCapacity()).isEqualTo(30);
    }
}
