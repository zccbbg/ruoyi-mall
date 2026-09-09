package com.cyl.manager.ums.domain.query;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 意见反馈 查询 对象
 *
 * @author zcc
 */
@Data
public class FeedbackQuery {
    /** 类型 精确匹配 */
    private String type;

    /** 具体说明 精确匹配 */
    private String content;

    /** 图片 精确匹配 */
    private String images;

    /** 联系电话 精确匹配 */
    private String phone;

    /** 处理状态 0：未处理  1：已处理 精确匹配 */
    private Integer handleStatus;

    /** 处理时间 精确匹配 */
    private LocalDateTime handleTime;

    private String beginTime;

    private String endTime;

}
