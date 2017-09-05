package com.hd123.zjlh.cubi.controller;
public class RestResponse<T> {
    private T payload;

    private boolean success;

    private String msg;

    private int status = -1;

    private long timestamp;

    public RestResponse() {
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    public RestResponse(boolean success) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
    }

    public RestResponse(boolean success, T payload) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
    }

    public RestResponse(boolean success, T payload, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
        this.status = code;
    }

    public RestResponse(boolean success, String msg) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
    }

    public RestResponse(boolean success, String msg, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
        this.status = code;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return status;
    }

    public void setCode(int code) {
        this.status = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static RestResponse ok() {
        return new RestResponse(true);
    }

    public static <T> RestResponse ok(T payload) {
        return new RestResponse(true, payload);
    }

    public static <T> RestResponse ok(int code) {
        return new RestResponse(true, null, code);
    }

    public static <T> RestResponse ok(T payload, int code) {
        return new RestResponse(true, payload, code);
    }

    public static RestResponse fail() {
        return new RestResponse(false);
    }

    public static RestResponse fail(String msg) {
        return new RestResponse(false, msg);
    }

    public static RestResponse fail(int code) {
        return new RestResponse(false, null, code);
    }

    public static RestResponse fail(int code, String msg) {
        return new RestResponse(false, msg, code);
    }

}