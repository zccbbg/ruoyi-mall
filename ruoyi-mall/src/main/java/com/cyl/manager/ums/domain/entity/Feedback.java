package com.cyl.manager.ums.domain.entity;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 意见反馈对象 ums_feedback
 * 
 * @author zcc
 */
@ApiModel(description="意见反馈对象")
@Data
@TableName("ums_feedback")
public class Feedback {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("类型")
    @Excel(name = "类型")
    private String type;

    @ApiModelProperty("具体说明")
    @Excel(name = "具体说明")
    private String content;

    @ApiModelProperty("图片")
    @Excel(name = "图片")
    private String images;

    @ApiModelProperty("联系电话")
    @Excel(name = "联系电话")
    private String phone;

    @ApiModelProperty("创建人")
    private Long createBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("处理状态 0：未处理  1：已处理")
    @Excel(name = "处理状态 0：未处理  1：已处理")
    private Integer handleStatus;

    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String remark;

    @ApiModelProperty("处理时间")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime handleTime;

}
