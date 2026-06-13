package com.erp.finance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 收付款单实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("fin_payment")
public class FinPayment extends BaseEntity {

    /** 收付款单编号 */
    private String paymentNo;

    /** 收付款类型（0=收款，1=付款） */
    private Integer paymentType;

    /** 账户ID */
    private Long accountId;

    /** 业务类型（purchase=采购，sale=销售） */
    private String businessType;

    /** 业务单据ID */
    private Long businessId;

    /** 业务单据编号 */
    private String businessNo;

    /** 金额 */
    private BigDecimal amount;

    /** 收付款日期 */
    private LocalDate paymentDate;

    /** 状态（0=草稿，1=已确认） */
    private Integer status;
}
