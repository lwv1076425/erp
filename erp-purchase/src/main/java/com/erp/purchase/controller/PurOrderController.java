package com.erp.purchase.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import com.erp.common.utils.BizUtils;
import com.erp.purchase.entity.PurOrder;
import com.erp.purchase.service.PurOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "采购订单管理")
@RestController
@RequestMapping("/purchase/order")
@RequiredArgsConstructor
public class PurOrderController {

    private final PurOrderService orderService;

    @Operation(summary = "采购订单列表")
    @GetMapping
    public Result<PageResult<PurOrder>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize,
            @RequestParam(required = false) String orderNo) {
        LambdaQueryWrapper<PurOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(orderNo != null, PurOrder::getOrderNo, orderNo);
        wrapper.orderByDesc(PurOrder::getCreateTime);
        return Result.ok(PageResult.of(orderService.page(new Page<>(pageNum, pageSize), wrapper)));
    }

    @Operation(summary = "采购订单详情")
    @GetMapping("/{id}")
    public Result<PurOrder> getInfo(@PathVariable Long id) {
        return Result.ok(orderService.getById(id));
    }

    @Operation(summary = "新增采购订单")
    @PostMapping
    public Result<Void> add(@RequestBody PurOrder order) {
        if (order.getOrderNo() == null) {
            order.setOrderNo(BizUtils.generateOrderNo("PO"));
        }
        orderService.save(order);
        return Result.ok();
    }

    @Operation(summary = "修改采购订单")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody PurOrder order) {
        order.setId(id);
        orderService.updateById(order);
        return Result.ok();
    }

    @Operation(summary = "删除采购订单")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        orderService.removeById(id);
        return Result.ok();
    }
}
