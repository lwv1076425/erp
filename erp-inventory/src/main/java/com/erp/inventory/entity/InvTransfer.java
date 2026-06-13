package com.erp.inventory.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 调拨单实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("inv_transfer")
public class InvTransfer extends BaseEntity {

    /** 调拨单编号 */
    private String transferNo;

    /** 调出仓库ID */
    private Long fromWarehouseId;

    /** 调入仓库ID */
    private Long toWarehouseId;

    /** 调拨日期 */
    private LocalDate transferDate;

    /** 状态（0=草稿，1=已确认） */
    private Integer status;
}
