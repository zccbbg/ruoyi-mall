package com.cyl.h5.pojo.response;

import lombok.Data;

@Data
public class ValidatePhoneResponse {
    /** 是否成功 */
    private boolean ifSuccess;
    /** 消息 */
    private String message;
}
