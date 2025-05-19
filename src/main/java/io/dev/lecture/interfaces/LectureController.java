package io.dev.lecture.interfaces;

import io.dev.lecture.application.LectureFacade;
import io.dev.lecture.application.dto.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureFacade lectureFacade;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerLecture(
            @RequestParam Long userId,
            @RequestParam Long scheduleId
    ) {
        RegistrationResponse response = lectureFacade.registrationLecture(userId, scheduleId);

        return ResponseEntity.ok(response);
    }
}
