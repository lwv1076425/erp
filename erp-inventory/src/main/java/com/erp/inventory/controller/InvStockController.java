package com.erp.inventory.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import com.erp.inventory.entity.InvStock;
import com.erp.inventory.service.InvStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "库存查询")
@RestController
@RequestMapping("/inventory/stock")
@RequiredArgsConstructor
public class InvStockController {

    private final InvStockService stockService;

    @Operation(summary = "库存列表")
    @GetMapping
    public Result<PageResult<InvStock>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize,
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) Long warehouseId) {
        LambdaQueryWrapper<InvStock> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(productId != null, InvStock::getProductId, productId);
        wrapper.eq(warehouseId != null, InvStock::getWarehouseId, warehouseId);
        return Result.ok(PageResult.of(stockService.page(new Page<>(pageNum, pageSize), wrapper)));
    }
}
