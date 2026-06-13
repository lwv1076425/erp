package com.erp.sales.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import com.erp.common.utils.BizUtils;
import com.erp.sales.entity.SalReturn;
import com.erp.sales.service.SalReturnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "销售退货管理")
@RestController
@RequestMapping("/sales/return")
@RequiredArgsConstructor
public class SalReturnController {

    private final SalReturnService returnService;

    @Operation(summary = "销售退货列表")
    @GetMapping
    public Result<PageResult<SalReturn>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize) {
        LambdaQueryWrapper<SalReturn> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(SalReturn::getCreateTime);
        return Result.ok(PageResult.of(returnService.page(new Page<>(pageNum, pageSize), wrapper)));
    }

    @Operation(summary = "新增销售退货")
    @PostMapping
    public Result<Void> add(@RequestBody SalReturn salReturn) {
        if (salReturn.getReturnNo() == null) {
            salReturn.setReturnNo(BizUtils.generateOrderNo("SR"));
        }
        returnService.save(salReturn);
        return Result.ok();
    }

    @Operation(summary = "删除销售退货")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        returnService.removeById(id);
        return Result.ok();
    }
}
