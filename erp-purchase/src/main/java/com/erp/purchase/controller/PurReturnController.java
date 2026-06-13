package com.erp.purchase.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import com.erp.common.utils.BizUtils;
import com.erp.purchase.entity.PurReturn;
import com.erp.purchase.service.PurReturnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "采购退货管理")
@RestController
@RequestMapping("/purchase/return")
@RequiredArgsConstructor
public class PurReturnController {

    private final PurReturnService returnService;

    @Operation(summary = "采购退货列表")
    @GetMapping
    public Result<PageResult<PurReturn>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize) {
        LambdaQueryWrapper<PurReturn> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(PurReturn::getCreateTime);
        return Result.ok(PageResult.of(returnService.page(new Page<>(pageNum, pageSize), wrapper)));
    }

    @Operation(summary = "新增采购退货")
    @PostMapping
    public Result<Void> add(@RequestBody PurReturn purReturn) {
        if (purReturn.getReturnNo() == null) {
            purReturn.setReturnNo(BizUtils.generateOrderNo("RT"));
        }
        returnService.save(purReturn);
        return Result.ok();
    }

    @Operation(summary = "删除采购退货")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        returnService.removeById(id);
        return Result.ok();
    }
}
