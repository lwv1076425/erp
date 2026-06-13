package com.erp.sales.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sal_return_item")
public class SalReturnItem extends BaseEntity {
    private Long returnId;
    private Long productId;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal amount;
}
