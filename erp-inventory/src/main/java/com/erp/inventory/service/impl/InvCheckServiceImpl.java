package com.erp.inventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.inventory.entity.InvCheck;
import com.erp.inventory.mapper.InvCheckMapper;
import com.erp.inventory.service.InvCheckService;
import org.springframework.stereotype.Service;

@Service
public class InvCheckServiceImpl extends ServiceImpl<InvCheckMapper, InvCheck> implements InvCheckService {
}
