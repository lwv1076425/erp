package com.erp.sales.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sal_delivery_item")
public class SalDeliveryItem extends BaseEntity {
    private Long deliveryId;
    private Long productId;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal amount;
}
