package io.dev.lecture.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저를 조회할 수없습니다."),
    LECTURE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 강의를 조회할 수 없습니다."),
    LECTURE_ALREADY_REGISTRATION(HttpStatus.NOT_FOUND, "이미 신청한 강의 입니다..");


    private final HttpStatus status;
    private final String message;
}
