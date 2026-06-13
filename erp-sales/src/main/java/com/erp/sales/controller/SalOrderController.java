package com.erp.sales.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import com.erp.common.utils.BizUtils;
import com.erp.sales.entity.SalOrder;
import com.erp.sales.service.SalOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "销售订单管理")
@RestController
@RequestMapping("/sales/order")
@RequiredArgsConstructor
public class SalOrderController {

    private final SalOrderService orderService;

    @Operation(summary = "销售订单列表")
    @GetMapping
    public Result<PageResult<SalOrder>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize,
            @RequestParam(required = false) String orderNo) {
        LambdaQueryWrapper<SalOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(orderNo != null, SalOrder::getOrderNo, orderNo);
        wrapper.orderByDesc(SalOrder::getCreateTime);
        return Result.ok(PageResult.of(orderService.page(new Page<>(pageNum, pageSize), wrapper)));
    }

    @Operation(summary = "销售订单详情")
    @GetMapping("/{id}")
    public Result<SalOrder> getInfo(@PathVariable Long id) {
        return Result.ok(orderService.getById(id));
    }

    @Operation(summary = "新增销售订单")
    @PostMapping
    public Result<Void> add(@RequestBody SalOrder order) {
        if (order.getOrderNo() == null) {
            order.setOrderNo(BizUtils.generateOrderNo("SO"));
        }
        orderService.save(order);
        return Result.ok();
    }

    @Operation(summary = "修改销售订单")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody SalOrder order) {
        order.setId(id);
        orderService.updateById(order);
        return Result.ok();
    }

    @Operation(summary = "删除销售订单")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        orderService.removeById(id);
        return Result.ok();
    }
}
