package com.jiraira.pruebaTec.application.utils;

public class Constants {
    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_NOTFOUND = 404;
    public static final String MESSAGE_OK = "success";
    public static final String MESSAGE_NOTFOUND = "Price not found";
    public static final String PRICE_NOT_FOUND_FORMAT = "Applicable price not found for productId: %d, brandId: %d, and date: %s.";

    public static final String MESSAGE_ERROR_NOTFOUND = "Price not found: %s";
    public static final String MESSAGE_ERROR_INTERNAL = "An unexpected error occurred: %s";
}
