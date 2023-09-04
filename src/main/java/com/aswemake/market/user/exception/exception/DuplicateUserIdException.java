package com.aswemake.market.user.exception.exception;

import com.aswemake.market.user.exception.ApplicationException;
import com.aswemake.market.user.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class DuplicateUserIdException extends ApplicationException {

    public DuplicateUserIdException() {
        super(HttpStatus.CONFLICT, ErrorCode.DUPLICATE_USERID_ERROR, "이미 존재하는 아이디 입니다.");
    }
}
