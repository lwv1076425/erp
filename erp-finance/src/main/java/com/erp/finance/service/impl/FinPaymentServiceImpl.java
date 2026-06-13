package com.erp.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.finance.entity.FinPayment;
import com.erp.finance.mapper.FinPaymentMapper;
import com.erp.finance.service.FinPaymentService;
import org.springframework.stereotype.Service;

@Service
public class FinPaymentServiceImpl extends ServiceImpl<FinPaymentMapper, FinPayment> implements FinPaymentService {
}
