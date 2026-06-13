package com.erp.finance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 发票实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("fin_invoice")
public class FinInvoice extends BaseEntity {

    /** 发票编号 */
    private String invoiceNo;

    /** 发票类型（0=销售发票，1=采购发票） */
    private Integer invoiceType;

    /** 业务类型 */
    private String businessType;

    /** 业务单据ID */
    private Long businessId;

    /** 业务单据编号 */
    private String businessNo;

    /** 金额 */
    private BigDecimal amount;

    /** 税额 */
    private BigDecimal taxAmount;

    /** 开票日期 */
    private LocalDate invoiceDate;

    /** 状态（0=草稿，1=已确认） */
    private Integer status;
}
