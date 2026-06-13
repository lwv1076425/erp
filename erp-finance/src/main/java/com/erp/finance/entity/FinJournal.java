package com.erp.finance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 日记账/凭证实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("fin_journal")
public class FinJournal extends BaseEntity {

    /** 凭证编号 */
    private String journalNo;

    /** 凭证日期 */
    private LocalDate journalDate;

    /** 业务类型 */
    private String businessType;

    /** 业务单据ID */
    private Long businessId;

    /** 业务单据编号 */
    private String businessNo;

    /** 借方金额 */
    private BigDecimal debitAmount;

    /** 贷方金额 */
    private BigDecimal creditAmount;

    /** 摘要 */
    private String summary;

    /** 状态（0=草稿，1=已确认） */
    private Integer status;
}
