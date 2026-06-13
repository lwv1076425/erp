package com.erp.finance.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import com.erp.common.utils.BizUtils;
import com.erp.finance.entity.FinJournal;
import com.erp.finance.service.FinJournalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "日记账管理")
@RestController
@RequestMapping("/finance/journal")
@RequiredArgsConstructor
public class FinJournalController {

    private final FinJournalService journalService;

    @Operation(summary = "日记账列表")
    @GetMapping
    public Result<PageResult<FinJournal>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize) {
        LambdaQueryWrapper<FinJournal> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(FinJournal::getCreateTime);
        return Result.ok(PageResult.of(journalService.page(new Page<>(pageNum, pageSize), wrapper)));
    }

    @Operation(summary = "新增日记账")
    @PostMapping
    public Result<Void> add(@RequestBody FinJournal journal) {
        if (journal.getJournalNo() == null) {
            journal.setJournalNo(BizUtils.generateOrderNo("JN"));
        }
        journalService.save(journal);
        return Result.ok();
    }

    @Operation(summary = "删除日记账")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        journalService.removeById(id);
        return Result.ok();
    }
}
