package io.dev.lecture.domain.service;

import io.dev.lecture.domain.model.Lecture;
import io.dev.lecture.domain.repository.LectureRepository;
import io.dev.lecture.support.CustomException;
import io.dev.lecture.support.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    public Lecture getLecture(long lectureId) {
        return lectureRepository.findById(lectureId)
                .orElseThrow(() -> new CustomException(ErrorCode.LECTURE_NOT_FOUND));
    }

    public Lecture getAvailableLecture(long lectureId) {
        return lectureRepository.findAvailableLectureById(lectureId)
                .orElseThrow(() -> new CustomException(ErrorCode.LECTURE_NOT_FOUND));
    }

    public void insertLecture(long scheduleId) {
        lectureRepository.increaseLecture(scheduleId);
    }
}
