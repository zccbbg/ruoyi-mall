package com.cyl.manager.oms.domain.vo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Jinxin
 * @date: 2022/4/22 14:12
 * @Description:
 */
@Getter
@Setter
/** 订单收获地址 */
public class OrderAddressVO {
    /** 收货人姓名 */
    private String name;
    /** 收货人手机号 */
    private String userPhone;
    /** 收获区域 */
    private String area;
    /** 详细地址 */
    private String address;

}
