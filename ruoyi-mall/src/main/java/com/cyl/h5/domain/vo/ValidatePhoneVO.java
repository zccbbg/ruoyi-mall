package com.cyl.h5.domain.vo;

import lombok.Data;

/**
 * todo 写法不规范，可以简化，没必要造一个类来处理
 */
@Data
public class ValidatePhoneVO {
    /** 是否成功 */
    private boolean ifSuccess;
    /** 消息 */
    private String message;
}
