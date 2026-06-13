package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 采购入库单实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pur_receipt")
public class PurReceipt extends BaseEntity {

    /** 入库单编号 */
    private String receiptNo;

    /** 采购订单ID */
    private Long orderId;

    /** 仓库ID */
    private Long warehouseId;

    /** 入库日期 */
    private LocalDate receiptDate;

    /** 状态（0=草稿，1=已确认） */
    private Integer status;
}
