package com.erp.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.finance.entity.FinInvoice;
import com.erp.finance.mapper.FinInvoiceMapper;
import com.erp.finance.service.FinInvoiceService;
import org.springframework.stereotype.Service;

@Service
public class FinInvoiceServiceImpl extends ServiceImpl<FinInvoiceMapper, FinInvoice> implements FinInvoiceService {
}
