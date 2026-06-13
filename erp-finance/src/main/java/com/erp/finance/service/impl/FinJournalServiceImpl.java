package com.erp.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.finance.entity.FinJournal;
import com.erp.finance.mapper.FinJournalMapper;
import com.erp.finance.service.FinJournalService;
import org.springframework.stereotype.Service;

@Service
public class FinJournalServiceImpl extends ServiceImpl<FinJournalMapper, FinJournal> implements FinJournalService {
}
