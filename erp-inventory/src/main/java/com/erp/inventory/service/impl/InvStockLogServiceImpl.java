package com.erp.inventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.inventory.entity.InvStockLog;
import com.erp.inventory.mapper.InvStockLogMapper;
import com.erp.inventory.service.InvStockLogService;
import org.springframework.stereotype.Service;

@Service
public class InvStockLogServiceImpl extends ServiceImpl<InvStockLogMapper, InvStockLog> implements InvStockLogService {
}
