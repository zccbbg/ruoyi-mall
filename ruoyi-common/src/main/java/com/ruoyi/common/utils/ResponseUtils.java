package com.ruoyi.common.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

public class ResponseUtils {

    private static final String MESSAGE = "msg";
    private static final String CODE = "code";
    private static final String DATA = "data";

    private ResponseUtils(){}

    private static ResponseEntity<Object> getEntity(Object body, HttpStatus statusCode){
        MultiValueMap<String, String> headers = new HttpHeaders();
        List<String> contentType = new ArrayList<String>();
        contentType.add("application/json;charset=utf-8");
        headers.put("Content-Type", contentType);
        return new ResponseEntity<Object>(body, headers, statusCode);
    }
}
