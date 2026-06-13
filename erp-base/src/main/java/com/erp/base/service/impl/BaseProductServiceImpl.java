package com.erp.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.base.entity.BaseProduct;
import com.erp.base.mapper.BaseProductMapper;
import com.erp.base.service.BaseProductService;
import org.springframework.stereotype.Service;

/**
 * 商品 Service 实现
 */
@Service
public class BaseProductServiceImpl extends ServiceImpl<BaseProductMapper, BaseProduct> implements BaseProductService {
}
