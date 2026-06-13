package com.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.base.entity.BaseProduct;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品 Mapper
 */
@Mapper
public interface BaseProductMapper extends BaseMapper<BaseProduct> {
}
