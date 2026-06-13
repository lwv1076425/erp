package com.erp.inventory.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 盘点单实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("inv_check")
public class InvCheck extends BaseEntity {

    /** 盘点单编号 */
    private String checkNo;

    /** 仓库ID */
    private Long warehouseId;

    /** 盘点日期 */
    private LocalDate checkDate;

    /** 状态（0=草稿，1=已确认） */
    private Integer status;
}
