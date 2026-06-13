package com.erp.sales.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 销售订单实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sal_order")
public class SalOrder extends BaseEntity {

    /** 订单编号 */
    private String orderNo;

    /** 客户ID */
    private Long customerId;

    /** 订单日期 */
    private LocalDate orderDate;

    /** 订单总金额 */
    private BigDecimal totalAmount;

    /** 状态（0=草稿，1=已审核，2=已出库，3=已完成，4=已取消） */
    private Integer status;
}
