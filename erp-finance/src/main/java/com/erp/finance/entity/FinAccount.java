package com.erp.finance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 账户实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("fin_account")
public class FinAccount extends BaseEntity {

    /** 账户名称 */
    private String accountName;

    /** 账户编码 */
    private String accountCode;

    /** 账户类型（0=银行账户，1=现金账户，2=其他） */
    private Integer accountType;

    /** 账户余额 */
    private BigDecimal balance;

    /** 状态（0=正常，1=停用） */
    private Integer status;
}
