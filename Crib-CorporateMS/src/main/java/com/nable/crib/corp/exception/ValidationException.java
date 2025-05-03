/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 10, 2022 - 11:51:24 AM
 *  ***************************************
 */

package com.nable.crib.corp.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@Getter
@Setter
public class ValidationException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final String errorCode;
    private final int status;

    public ValidationException(String errorCode, int status, String message) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

    public ValidationException(String errorCode, int status) {
        this.errorCode = errorCode;
        this.status = status;
    }

    public ValidationException(String message, Throwable cause, String errorCode, int status) {
        super(message, cause);
        this.errorCode = errorCode;
        this.status = status;
    }

}
