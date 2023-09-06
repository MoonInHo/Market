package com.aswemake.market.exception.exception;

import com.aswemake.market.exception.ApplicationException;
import com.aswemake.market.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class EmptyProductListException extends ApplicationException {

    public EmptyProductListException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.EMPTY_PRODUCT_LIST_ERROR, "상품 목록이 비어있습니다.");
    }
}
