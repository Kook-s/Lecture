package io.dev.lecture.domain.service;

import io.dev.lecture.domain.model.Schedule;
import io.dev.lecture.domain.repository.LectureRepository;
import io.dev.lecture.support.CustomException;
import io.dev.lecture.support.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    public Schedule getLecture(long id) {
        return lectureRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.LECTURE_NOT_FOUND));
    }

    public List<Schedule> getAvailableLectures(LocalDateTime date) {
        return lectureRepository.findAvailableById(date);
    }

    public List<Schedule> getAllLectures(List<Long> lectureIds) {
        return lectureRepository.findAllLectures(lectureIds);
    }

    public int increaseCapacity(long id) {
        return lectureRepository.increaseLecture(id);
    }

}
