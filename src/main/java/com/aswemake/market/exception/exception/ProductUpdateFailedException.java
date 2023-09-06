package com.aswemake.market.exception.exception;

import com.aswemake.market.exception.ApplicationException;
import com.aswemake.market.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class ProductUpdateFailedException extends ApplicationException {

    public ProductUpdateFailedException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.UPDATE_FAILED_ERROR, "정보 업데이트에 실패했습니다.");
    }
}
