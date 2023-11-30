package com.jrbox.springbootmongodb.exception;

import com.jrbox.springbootmongodb.domain.CommonBusinessError;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class BusinessErrorException extends RuntimeException {
    @NonNull
    private CommonBusinessError businessError;
    private String[] parameters;
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public BusinessErrorException(CommonBusinessError businessError, HttpStatus httpStatus) {
        this.setBusinessError(businessError);
        this.setHttpStatus(httpStatus);
    }

    public BusinessErrorException(CommonBusinessError businessError, String value, HttpStatus httpStatus) {
        this(businessError, httpStatus);
        this.setParameters(new String[]{value});
    }

    public BusinessErrorException(CommonBusinessError businessError, String value) {
        this(businessError, value, HttpStatus.BAD_REQUEST);
    }

    public BusinessErrorException(CommonBusinessError businessError, int value) {
        this(businessError, value, HttpStatus.BAD_REQUEST);
    }

    public BusinessErrorException(CommonBusinessError businessError, int value, HttpStatus httpStatus) {
        this(businessError, new String[]{String.valueOf(value)}, httpStatus);
    }
}
