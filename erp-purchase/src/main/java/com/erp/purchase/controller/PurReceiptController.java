package com.erp.purchase.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import com.erp.common.utils.BizUtils;
import com.erp.purchase.entity.PurReceipt;
import com.erp.purchase.service.PurReceiptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "采购入库管理")
@RestController
@RequestMapping("/purchase/receipt")
@RequiredArgsConstructor
public class PurReceiptController {

    private final PurReceiptService receiptService;

    @Operation(summary = "采购入库列表")
    @GetMapping
    public Result<PageResult<PurReceipt>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize) {
        LambdaQueryWrapper<PurReceipt> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(PurReceipt::getCreateTime);
        return Result.ok(PageResult.of(receiptService.page(new Page<>(pageNum, pageSize), wrapper)));
    }

    @Operation(summary = "新增采购入库")
    @PostMapping
    public Result<Void> add(@RequestBody PurReceipt receipt) {
        if (receipt.getReceiptNo() == null) {
            receipt.setReceiptNo(BizUtils.generateOrderNo("PR"));
        }
        receiptService.save(receipt);
        return Result.ok();
    }

    @Operation(summary = "删除采购入库")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        receiptService.removeById(id);
        return Result.ok();
    }
}
