package com.erp.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询参数
 */
@Data
public class PageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 当前页码，默认 1 */
    private long pageNum = 1;

    /** 每页大小，默认 10 */
    private long pageSize = 10;

    /** 排序字段 */
    private String orderByColumn;

    /** 排序方向 asc/desc */
    private String isAsc;
}
