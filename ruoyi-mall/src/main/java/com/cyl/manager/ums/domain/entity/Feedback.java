package com.cyl.manager.ums.domain.entity;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 意见反馈对象 ums_feedback
 * 
 * @author zcc
 */
@Data
@TableName("ums_feedback")
public class Feedback {
    private static final long serialVersionUID = 1L;

    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 具体说明 */
    @Excel(name = "具体说明")
    private String content;

    /** 图片 */
    @Excel(name = "图片")
    private String images;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 创建人 */
    private Long createBy;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 处理状态 0：未处理  1：已处理 */
    @Excel(name = "处理状态 0：未处理  1：已处理")
    private Integer handleStatus;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 处理时间 */
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime handleTime;

}
