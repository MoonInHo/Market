package com.aswemake.market.exception.exception;

import com.aswemake.market.exception.ApplicationException;
import com.aswemake.market.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ApplicationException {

    public ProductNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.PRODUCT_NOT_FOUND_ERROR, "존재하지 않는 상품입니다.");
    }
}
