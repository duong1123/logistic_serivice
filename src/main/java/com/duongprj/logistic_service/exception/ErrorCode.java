package com.duongprj.logistic_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(1000, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1001, "This username is already taken", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(1002, "Username must have at least 6 characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1003, "Password must have at least 8 characters", HttpStatus.BAD_REQUEST),
    INVALID_MESSAGE_KEY(1004, "Invalid message key", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User does not exist", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "Unauthorized", HttpStatus.FORBIDDEN),
    UNIT_EXISTED(1008, "This unit is already existed", HttpStatus.BAD_REQUEST),
    UNIT_NOT_EXISTED(1009, "This unit does not exist", HttpStatus.NOT_FOUND),
    PARCEL_CANCELLED(1010, "This parcel has been cancelled", HttpStatus.BAD_REQUEST),
    PARCEL_ALREADY_IN_ANOTHER_BATCH(1011, "This parcel is already in another batch", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

}
