package io.dev.lecture.interfaces;

import io.dev.lecture.application.LectureFacade;
import io.dev.lecture.application.dto.RegistrationResponse;
import io.dev.lecture.application.dto.ScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/available")
    public ResponseEntity<List<ScheduleResponse>> getAvailableLectures(@RequestParam("date") String dateStr) {
        LocalDateTime date = LocalDateTime.parse(dateStr);

        List<ScheduleResponse> responses = lectureFacade.AvailableLecture(date);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/registration/{userId}")
    public ResponseEntity<List<ScheduleResponse>> getRegistration(@PathVariable Long userId) {
        List<ScheduleResponse> responses = lectureFacade.getRegisteredLectures(userId);
        return ResponseEntity.ok(responses);
    }
}
