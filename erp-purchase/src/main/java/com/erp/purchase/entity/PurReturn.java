package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 采购退货单实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pur_return")
public class PurReturn extends BaseEntity {

    /** 退货单编号 */
    private String returnNo;

    /** 采购订单ID */
    private Long orderId;

    /** 供应商ID */
    private Long supplierId;

    /** 退货日期 */
    private LocalDate returnDate;

    /** 退货总金额 */
    private BigDecimal totalAmount;

    /** 状态（0=草稿，1=已确认） */
    private Integer status;
}
