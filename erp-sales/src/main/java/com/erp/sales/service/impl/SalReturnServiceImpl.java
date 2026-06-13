package com.erp.sales.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.sales.entity.SalReturn;
import com.erp.sales.mapper.SalReturnMapper;
import com.erp.sales.service.SalReturnService;
import org.springframework.stereotype.Service;

@Service
public class SalReturnServiceImpl extends ServiceImpl<SalReturnMapper, SalReturn> implements SalReturnService {
}
