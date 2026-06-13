package com.erp.inventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.inventory.entity.InvTransfer;
import com.erp.inventory.mapper.InvTransferMapper;
import com.erp.inventory.service.InvTransferService;
import org.springframework.stereotype.Service;

@Service
public class InvTransferServiceImpl extends ServiceImpl<InvTransferMapper, InvTransfer> implements InvTransferService {
}
