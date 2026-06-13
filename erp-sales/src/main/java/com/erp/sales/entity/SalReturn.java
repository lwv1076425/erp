package com.erp.sales.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sal_return")
public class SalReturn extends BaseEntity {
    private String returnNo;
    private Long orderId;
    private Long customerId;
    private LocalDate returnDate;
    private BigDecimal totalAmount;
    private Integer status;
}
