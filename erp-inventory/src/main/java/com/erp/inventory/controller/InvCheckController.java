package com.erp.inventory.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import com.erp.common.utils.BizUtils;
import com.erp.inventory.entity.InvCheck;
import com.erp.inventory.service.InvCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "库存盘点")
@RestController
@RequestMapping("/inventory/check")
@RequiredArgsConstructor
public class InvCheckController {

    private final InvCheckService checkService;

    @Operation(summary = "盘点列表")
    @GetMapping
    public Result<PageResult<InvCheck>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize) {
        LambdaQueryWrapper<InvCheck> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(InvCheck::getCreateTime);
        return Result.ok(PageResult.of(checkService.page(new Page<>(pageNum, pageSize), wrapper)));
    }

    @Operation(summary = "新增盘点")
    @PostMapping
    public Result<Void> add(@RequestBody InvCheck check) {
        if (check.getCheckNo() == null) {
            check.setCheckNo(BizUtils.generateOrderNo("CK"));
        }
        checkService.save(check);
        return Result.ok();
    }

    @Operation(summary = "删除盘点")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        checkService.removeById(id);
        return Result.ok();
    }
}
