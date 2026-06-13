package com.erp.sales.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sal_delivery")
public class SalDelivery extends BaseEntity {
    private String deliveryNo;
    private Long orderId;
    private Long warehouseId;
    private LocalDate deliveryDate;
    private Integer status;
}
