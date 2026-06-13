package com.erp.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import com.erp.system.entity.SysMenu;
import com.erp.system.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理控制器
 */
@Tag(name = "菜单管理")
@RestController
@RequestMapping("/system/menu")
@RequiredArgsConstructor
public class SysMenuController {

    private final SysMenuService menuService;

    @Operation(summary = "菜单列表")
    @GetMapping
    public Result<PageResult<SysMenu>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize) {

        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(SysMenu::getSort);

        Page<SysMenu> page = menuService.page(new Page<>(pageNum, pageSize), wrapper);
        return Result.ok(PageResult.of(page));
    }

    @Operation(summary = "菜单详情")
    @GetMapping("/{id}")
    public Result<SysMenu> getInfo(@PathVariable Long id) {
        SysMenu menu = menuService.getById(id);
        if (menu == null) {
            return Result.fail("菜单不存在");
        }
        return Result.ok(menu);
    }

    @Operation(summary = "新增菜单")
    @PostMapping
    public Result<Void> add(@RequestBody SysMenu menu) {
        menuService.save(menu);
        return Result.ok();
    }

    @Operation(summary = "修改菜单")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody SysMenu menu) {
        menu.setId(id);
        menuService.updateById(menu);
        return Result.ok();
    }

    @Operation(summary = "删除菜单")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        menuService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "菜单树")
    @GetMapping("/tree")
    public Result<List<SysMenu>> tree() {
        return Result.ok(menuService.getMenuTree());
    }

    @Operation(summary = "获取路由")
    @GetMapping("/routers")
    public Result<List<SysMenu>> routers() {
        return Result.ok(menuService.getRouters());
    }
}
