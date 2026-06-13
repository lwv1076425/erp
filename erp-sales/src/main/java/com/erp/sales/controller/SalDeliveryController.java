package com.erp.sales.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import com.erp.common.utils.BizUtils;
import com.erp.sales.entity.SalDelivery;
import com.erp.sales.service.SalDeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "销售出库管理")
@RestController
@RequestMapping("/sales/delivery")
@RequiredArgsConstructor
public class SalDeliveryController {

    private final SalDeliveryService deliveryService;

    @Operation(summary = "销售出库列表")
    @GetMapping
    public Result<PageResult<SalDelivery>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize) {
        LambdaQueryWrapper<SalDelivery> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(SalDelivery::getCreateTime);
        return Result.ok(PageResult.of(deliveryService.page(new Page<>(pageNum, pageSize), wrapper)));
    }

    @Operation(summary = "新增销售出库")
    @PostMapping
    public Result<Void> add(@RequestBody SalDelivery delivery) {
        if (delivery.getDeliveryNo() == null) {
            delivery.setDeliveryNo(BizUtils.generateOrderNo("SD"));
        }
        deliveryService.save(delivery);
        return Result.ok();
    }

    @Operation(summary = "删除销售出库")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        deliveryService.removeById(id);
        return Result.ok();
    }
}
