package com.erp.inventory.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 调拨明细实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("inv_transfer_item")
public class InvTransferItem extends BaseEntity {

    /** 调拨单ID */
    private Long transferId;

    /** 商品ID */
    private Long productId;

    /** 数量 */
    private BigDecimal quantity;
}
