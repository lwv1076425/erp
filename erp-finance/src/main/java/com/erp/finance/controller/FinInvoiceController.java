package com.erp.finance.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import com.erp.common.utils.BizUtils;
import com.erp.finance.entity.FinInvoice;
import com.erp.finance.service.FinInvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "发票管理")
@RestController
@RequestMapping("/finance/invoice")
@RequiredArgsConstructor
public class FinInvoiceController {

    private final FinInvoiceService invoiceService;

    @Operation(summary = "发票列表")
    @GetMapping
    public Result<PageResult<FinInvoice>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize) {
        LambdaQueryWrapper<FinInvoice> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(FinInvoice::getCreateTime);
        return Result.ok(PageResult.of(invoiceService.page(new Page<>(pageNum, pageSize), wrapper)));
    }

    @Operation(summary = "新增发票")
    @PostMapping
    public Result<Void> add(@RequestBody FinInvoice invoice) {
        if (invoice.getInvoiceNo() == null) {
            invoice.setInvoiceNo(BizUtils.generateOrderNo("INV"));
        }
        invoiceService.save(invoice);
        return Result.ok();
    }

    @Operation(summary = "删除发票")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        invoiceService.removeById(id);
        return Result.ok();
    }
}
