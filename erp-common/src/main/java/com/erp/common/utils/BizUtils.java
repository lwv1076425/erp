package com.erp.common.utils;

import cn.hutool.core.util.IdUtil;

/**
 * 业务工具类
 */
public class BizUtils {

    /**
     * 生成业务单号
     * 格式：前缀 + 日期 + 6位随机数
     */
    public static String generateOrderNo(String prefix) {
        return prefix + cn.hutool.core.date.DateUtil.format(new java.util.Date(), "yyyyMMdd") + IdUtil.getSnowflakeNextIdStr().substring(0, 6);
    }

    /**
     * 生成唯一编码
     */
    public static String generateCode() {
        return IdUtil.fastSimpleUUID();
    }
}
