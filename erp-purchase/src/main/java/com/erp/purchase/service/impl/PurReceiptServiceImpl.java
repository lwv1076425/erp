package com.erp.purchase.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.purchase.entity.PurReceipt;
import com.erp.purchase.mapper.PurReceiptMapper;
import com.erp.purchase.service.PurReceiptService;
import org.springframework.stereotype.Service;

@Service
public class PurReceiptServiceImpl extends ServiceImpl<PurReceiptMapper, PurReceipt> implements PurReceiptService {
}
