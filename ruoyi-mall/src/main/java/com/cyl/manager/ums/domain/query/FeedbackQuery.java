package com.cyl.manager.ums.domain.query;

import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 意见反馈 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="意见反馈 查询 对象")
@Data
public class FeedbackQuery {
    @ApiModelProperty("类型 精确匹配")
    private String type;

    @ApiModelProperty("具体说明 精确匹配")
    private String content;

    @ApiModelProperty("图片 精确匹配")
    private String images;

    @ApiModelProperty("联系电话 精确匹配")
    private String phone;

    @ApiModelProperty("处理状态 0：未处理  1：已处理 精确匹配")
    private Integer handleStatus;

    @ApiModelProperty("处理时间 精确匹配")
    private LocalDateTime handleTime;

    private String beginTime;

    private String endTime;

}
