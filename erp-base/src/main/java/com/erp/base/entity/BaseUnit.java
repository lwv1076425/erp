package com.erp.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 计量单位实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("base_unit")
public class BaseUnit extends BaseEntity {

    /** 单位名称 */
    private String unitName;

    /** 单位编码 */
    private String unitCode;

    /** 排序 */
    private Integer sort;

    /** 状态（0=正常，1=停用） */
    private Integer status;
}
