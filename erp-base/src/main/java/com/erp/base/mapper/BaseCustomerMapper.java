package com.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.base.entity.BaseCustomer;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户 Mapper
 */
@Mapper
public interface BaseCustomerMapper extends BaseMapper<BaseCustomer> {
}
