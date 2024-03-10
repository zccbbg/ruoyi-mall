package com.cyl.h5.domain.vo;

import lombok.Data;

@Data
public class ValidatePhoneResponse {
    /** 是否成功 */
    private boolean ifSuccess;
    /** 消息 */
    private String message;
}
