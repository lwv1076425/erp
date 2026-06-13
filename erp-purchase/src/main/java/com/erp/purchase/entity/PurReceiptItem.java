package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 采购入库明细实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pur_receipt_item")
public class PurReceiptItem extends BaseEntity {

    /** 入库单ID */
    private Long receiptId;

    /** 商品ID */
    private Long productId;

    /** 数量 */
    private BigDecimal quantity;

    /** 单价 */
    private BigDecimal price;

    /** 金额 */
    private BigDecimal amount;
}
