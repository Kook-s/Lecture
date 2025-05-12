package io.dev.lecture.interfaces;

import io.dev.lecture.application.LectureFacade;
import io.dev.lecture.domain.model.Lecture;
import io.dev.lecture.interfaces.dto.LectureHttp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureFacade lectureFacade;

    @PostMapping("/register")
    public ResponseEntity<Void> registration(
            @RequestBody LectureHttp.RegistrationLecture.Request request
    ) {
        lectureFacade.registerLecture(request.userId(), request.lectureId(), request.lectureScheduleId());
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/available")
    public ResponseEntity<LectureHttp.AvailableLectures.Response> availableLectures(
            LectureHttp.AvailableLectures.Request request
            ) {
        List<Lecture> lectureResult = lectureFacade.getAvailableLectures(request.date());
        return ResponseEntity.ok(new LectureHttp.AvailableLectures.Response(lectureResult));
    }

    @GetMapping("/registrations")
    public ResponseEntity<LectureHttp.RegistrationUserLecture.Response> registrations(
            LectureHttp.RegistrationUserLecture.Request request
    ) {
        List<Lecture> result = lectureFacade.getRegistrationLecture(request.userId());

        return ResponseEntity.ok(new LectureHttp.RegistrationUserLecture.Response(result));
    }
}
