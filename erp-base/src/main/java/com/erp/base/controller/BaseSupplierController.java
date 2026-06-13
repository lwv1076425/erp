package com.erp.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.base.entity.BaseSupplier;
import com.erp.base.service.BaseSupplierService;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 供应商管理控制器
 */
@Tag(name = "供应商管理")
@RestController
@RequestMapping("/base/supplier")
@RequiredArgsConstructor
public class BaseSupplierController {

    private final BaseSupplierService supplierService;

    @Operation(summary = "供应商列表")
    @GetMapping
    public Result<PageResult<BaseSupplier>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize,
            @RequestParam(required = false) String supplierName) {

        LambdaQueryWrapper<BaseSupplier> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(supplierName), BaseSupplier::getSupplierName, supplierName);
        wrapper.orderByDesc(BaseSupplier::getCreateTime);

        Page<BaseSupplier> page = supplierService.page(new Page<>(pageNum, pageSize), wrapper);
        return Result.ok(PageResult.of(page));
    }

    @Operation(summary = "供应商详情")
    @GetMapping("/{id}")
    public Result<BaseSupplier> getInfo(@PathVariable Long id) {
        return Result.ok(supplierService.getById(id));
    }

    @Operation(summary = "新增供应商")
    @PostMapping
    public Result<Void> add(@RequestBody BaseSupplier supplier) {
        supplierService.save(supplier);
        return Result.ok();
    }

    @Operation(summary = "修改供应商")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody BaseSupplier supplier) {
        supplier.setId(id);
        supplierService.updateById(supplier);
        return Result.ok();
    }

    @Operation(summary = "删除供应商")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        supplierService.removeById(id);
        return Result.ok();
    }
}
