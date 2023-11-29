package com.jrbox.springbootmongodb.exception;

import com.jrbox.springbootmongodb.domain.BusinessError;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class BusinessErrorException extends RuntimeException {
    @NonNull
    private BusinessError businessError;
    private String[] parameters;
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public BusinessErrorException(BusinessError businessError, HttpStatus httpStatus) {
        this.setBusinessError(businessError);
        this.setHttpStatus(httpStatus);
    }

    public BusinessErrorException(BusinessError businessError, String value, HttpStatus httpStatus) {
        this(businessError, httpStatus);
        this.setParameters(new String[]{value});
    }

    public BusinessErrorException(BusinessError businessError, String value) {
        this(businessError, value, HttpStatus.BAD_REQUEST);
    }

    public BusinessErrorException(BusinessError businessError, int value) {
        this(businessError, value, HttpStatus.BAD_REQUEST);
    }

    public BusinessErrorException(BusinessError businessError, int value, HttpStatus httpStatus) {
        this(businessError, new String[]{String.valueOf(value)}, httpStatus);
    }
}
