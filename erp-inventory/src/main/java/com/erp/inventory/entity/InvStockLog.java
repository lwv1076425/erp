package com.erp.inventory.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 库存流水实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("inv_stock_log")
public class InvStockLog extends BaseEntity {

    /** 商品ID */
    private Long productId;

    /** 仓库ID */
    private Long warehouseId;

    /** 业务类型 */
    private String businessType;

    /** 业务单据ID */
    private Long businessId;

    /** 业务单据编号 */
    private String businessNo;

    /** 数量（正数=入库，负数=出库） */
    private BigDecimal quantity;

    /** 变动前库存 */
    private BigDecimal beforeQuantity;

    /** 变动后库存 */
    private BigDecimal afterQuantity;
}
