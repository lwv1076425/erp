package com.erp.sales.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.sales.entity.SalOrder;
import com.erp.sales.mapper.SalOrderMapper;
import com.erp.sales.service.SalOrderService;
import org.springframework.stereotype.Service;

@Service
public class SalOrderServiceImpl extends ServiceImpl<SalOrderMapper, SalOrder> implements SalOrderService {
}
