package com.aswemake.market.exception;

public enum ErrorCode {

    // Global
    INVALID_REQUEST,
    UPDATE_FAILED_ERROR,

    // Member
    DUPLICATE_USERID_ERROR,

    // Product
    PRODUCT_NOT_FOUND_ERROR,
    EMPTY_PRODUCT_LIST_ERROR,
    DUPLICATE_PRODUCT_NAME_ERROR,

    // Order
    EMPTY_ORDER_LIST_ERROR,

    // Coupon
    COUPON_NOT_FOUND_ERROR,
}