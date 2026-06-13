package com.erp.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.base.entity.BaseWarehouse;
import com.erp.base.mapper.BaseWarehouseMapper;
import com.erp.base.service.BaseWarehouseService;
import org.springframework.stereotype.Service;

/**
 * 仓库 Service 实现
 */
@Service
public class BaseWarehouseServiceImpl extends ServiceImpl<BaseWarehouseMapper, BaseWarehouse> implements BaseWarehouseService {
}
