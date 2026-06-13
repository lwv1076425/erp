package com.erp.purchase.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.purchase.entity.PurReturn;
import com.erp.purchase.mapper.PurReturnMapper;
import com.erp.purchase.service.PurReturnService;
import org.springframework.stereotype.Service;

@Service
public class PurReturnServiceImpl extends ServiceImpl<PurReturnMapper, PurReturn> implements PurReturnService {
}
