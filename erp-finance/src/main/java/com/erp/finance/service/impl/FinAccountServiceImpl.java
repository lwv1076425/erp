package com.erp.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.finance.entity.FinAccount;
import com.erp.finance.mapper.FinAccountMapper;
import com.erp.finance.service.FinAccountService;
import org.springframework.stereotype.Service;

@Service
public class FinAccountServiceImpl extends ServiceImpl<FinAccountMapper, FinAccount> implements FinAccountService {
}
