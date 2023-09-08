package com.aswemake.market.exception.exception;

import com.aswemake.market.exception.ApplicationException;
import com.aswemake.market.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class CouponNotFoundException extends ApplicationException {

    public CouponNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.COUPON_NOT_FOUND_ERROR, "존재하지 않는 쿠폰입니다.");
    }
}
