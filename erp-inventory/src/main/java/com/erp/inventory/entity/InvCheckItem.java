package com.erp.inventory.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 盘点明细实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("inv_check_item")
public class InvCheckItem extends BaseEntity {

    /** 盘点单ID */
    private Long checkId;

    /** 商品ID */
    private Long productId;

    /** 系统库存 */
    private BigDecimal systemQuantity;

    /** 实际库存 */
    private BigDecimal actualQuantity;

    /** 差异数量 */
    private BigDecimal diffQuantity;
}
