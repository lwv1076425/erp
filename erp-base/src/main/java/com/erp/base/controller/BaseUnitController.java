package com.erp.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.base.entity.BaseUnit;
import com.erp.base.service.BaseUnitService;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 计量单位管理控制器
 */
@Tag(name = "计量单位管理")
@RestController
@RequestMapping("/base/unit")
@RequiredArgsConstructor
public class BaseUnitController {

    private final BaseUnitService unitService;

    @Operation(summary = "计量单位列表")
    @GetMapping
    public Result<PageResult<BaseUnit>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize) {

        LambdaQueryWrapper<BaseUnit> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(BaseUnit::getSort);

        Page<BaseUnit> page = unitService.page(new Page<>(pageNum, pageSize), wrapper);
        return Result.ok(PageResult.of(page));
    }

    @Operation(summary = "新增计量单位")
    @PostMapping
    public Result<Void> add(@RequestBody BaseUnit unit) {
        unitService.save(unit);
        return Result.ok();
    }

    @Operation(summary = "修改计量单位")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody BaseUnit unit) {
        unit.setId(id);
        unitService.updateById(unit);
        return Result.ok();
    }

    @Operation(summary = "删除计量单位")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        unitService.removeById(id);
        return Result.ok();
    }
}
