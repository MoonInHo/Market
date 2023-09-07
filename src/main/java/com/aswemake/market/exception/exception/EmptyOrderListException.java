package com.aswemake.market.exception.exception;

import com.aswemake.market.exception.ApplicationException;
import com.aswemake.market.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class EmptyOrderListException extends ApplicationException {

    public EmptyOrderListException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.EMPTY_ORDER_LIST_ERROR, "주문 목록이 비어있습니다.");
    }
}
