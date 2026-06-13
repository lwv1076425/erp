package com.erp.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用状态枚举
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    /** 启用 */
    ENABLE(0, "启用"),
    /** 禁用 */
    DISABLE(1, "禁用");

    private final int code;
    private final String desc;

    public static StatusEnum of(int code) {
        for (StatusEnum status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }
}
