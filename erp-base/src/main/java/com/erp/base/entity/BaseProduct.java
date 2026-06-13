package com.erp.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("base_product")
public class BaseProduct extends BaseEntity {

    /** 商品编码 */
    private String productCode;

    /** 商品名称 */
    private String productName;

    /** 分类ID */
    private Long categoryId;

    /** 计量单位ID */
    private Long unitId;

    /** 规格型号 */
    private String spec;

    /** 条形码 */
    private String barcode;

    /** 采购价 */
    private BigDecimal purchasePrice;

    /** 销售价 */
    private BigDecimal salePrice;

    /** 最低库存 */
    private BigDecimal minStock;

    /** 最高库存 */
    private BigDecimal maxStock;

    /** 状态（0=正常，1=停用） */
    private Integer status;
}
