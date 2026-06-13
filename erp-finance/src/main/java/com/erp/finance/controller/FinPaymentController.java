package com.erp.finance.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import com.erp.common.utils.BizUtils;
import com.erp.finance.entity.FinPayment;
import com.erp.finance.service.FinPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "收付款管理")
@RestController
@RequestMapping("/finance/payment")
@RequiredArgsConstructor
public class FinPaymentController {

    private final FinPaymentService paymentService;

    @Operation(summary = "收付款列表")
    @GetMapping
    public Result<PageResult<FinPayment>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize) {
        LambdaQueryWrapper<FinPayment> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(FinPayment::getCreateTime);
        return Result.ok(PageResult.of(paymentService.page(new Page<>(pageNum, pageSize), wrapper)));
    }

    @Operation(summary = "新增收付款")
    @PostMapping
    public Result<Void> add(@RequestBody FinPayment payment) {
        if (payment.getPaymentNo() == null) {
            payment.setPaymentNo(BizUtils.generateOrderNo("PY"));
        }
        paymentService.save(payment);
        return Result.ok();
    }

    @Operation(summary = "删除收付款")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        paymentService.removeById(id);
        return Result.ok();
    }
}
