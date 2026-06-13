package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 采购订单明细实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pur_order_item")
public class PurOrderItem extends BaseEntity {

    /** 订单ID */
    private Long orderId;

    /** 商品ID */
    private Long productId;

    /** 数量 */
    private BigDecimal quantity;

    /** 单价 */
    private BigDecimal price;

    /** 金额 */
    private BigDecimal amount;

    /** 已入库数量 */
    private BigDecimal receivedQuantity;
}
