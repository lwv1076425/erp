package com.erp.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.base.entity.BaseWarehouse;
import com.erp.base.service.BaseWarehouseService;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 仓库管理控制器
 */
@Tag(name = "仓库管理")
@RestController
@RequestMapping("/base/warehouse")
@RequiredArgsConstructor
public class BaseWarehouseController {

    private final BaseWarehouseService warehouseService;

    @Operation(summary = "仓库列表")
    @GetMapping
    public Result<PageResult<BaseWarehouse>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize,
            @RequestParam(required = false) String warehouseName) {

        LambdaQueryWrapper<BaseWarehouse> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(warehouseName), BaseWarehouse::getWarehouseName, warehouseName);
        wrapper.orderByDesc(BaseWarehouse::getCreateTime);

        Page<BaseWarehouse> page = warehouseService.page(new Page<>(pageNum, pageSize), wrapper);
        return Result.ok(PageResult.of(page));
    }

    @Operation(summary = "仓库详情")
    @GetMapping("/{id}")
    public Result<BaseWarehouse> getInfo(@PathVariable Long id) {
        return Result.ok(warehouseService.getById(id));
    }

    @Operation(summary = "新增仓库")
    @PostMapping
    public Result<Void> add(@RequestBody BaseWarehouse warehouse) {
        warehouseService.save(warehouse);
        return Result.ok();
    }

    @Operation(summary = "修改仓库")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody BaseWarehouse warehouse) {
        warehouse.setId(id);
        warehouseService.updateById(warehouse);
        return Result.ok();
    }

    @Operation(summary = "删除仓库")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        warehouseService.removeById(id);
        return Result.ok();
    }
}
