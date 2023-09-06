package com.aswemake.market.exception.exception;

import com.aswemake.market.exception.ApplicationException;
import com.aswemake.market.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class DuplicateProductNameException extends ApplicationException {

    public DuplicateProductNameException() {
        super(HttpStatus.CONFLICT, ErrorCode.DUPLICATE_PRODUCT_NAME_ERROR, "이미 존재하는 상품입니다.");
    }
}
