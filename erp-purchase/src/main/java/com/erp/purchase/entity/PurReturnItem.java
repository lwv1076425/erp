package com.erp.purchase.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 采购退货明细实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pur_return_item")
public class PurReturnItem extends BaseEntity {

    /** 退货单ID */
    private Long returnId;

    /** 商品ID */
    private Long productId;

    /** 数量 */
    private BigDecimal quantity;

    /** 单价 */
    private BigDecimal price;

    /** 金额 */
    private BigDecimal amount;
}
