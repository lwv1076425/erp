package com.erp.inventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.inventory.entity.InvStock;
import com.erp.inventory.mapper.InvStockMapper;
import com.erp.inventory.service.InvStockService;
import org.springframework.stereotype.Service;

@Service
public class InvStockServiceImpl extends ServiceImpl<InvStockMapper, InvStock> implements InvStockService {
}
