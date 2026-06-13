package com.erp.purchase.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.purchase.entity.PurOrder;
import com.erp.purchase.mapper.PurOrderMapper;
import com.erp.purchase.service.PurOrderService;
import org.springframework.stereotype.Service;

@Service
public class PurOrderServiceImpl extends ServiceImpl<PurOrderMapper, PurOrder> implements PurOrderService {
}
