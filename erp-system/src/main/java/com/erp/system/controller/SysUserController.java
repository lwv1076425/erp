package com.erp.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import com.erp.common.utils.ExcelUtils;
import com.erp.system.entity.SysUser;
import com.erp.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理控制器
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/system/user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService userService;

    @Operation(summary = "用户列表")
    @GetMapping
    public Result<PageResult<SysUser>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String nickname) {

        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(username), SysUser::getUsername, username);
        wrapper.like(StringUtils.hasText(nickname), SysUser::getNickname, nickname);
        wrapper.orderByDesc(SysUser::getCreateTime);

        Page<SysUser> page = userService.page(new Page<>(pageNum, pageSize), wrapper);
        return Result.ok(PageResult.of(page));
    }

    @Operation(summary = "用户详情")
    @GetMapping("/{id}")
    public Result<SysUser> getInfo(@PathVariable Long id) {
        SysUser user = userService.getById(id);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        user.setPassword(null); // 不返回密码
        return Result.ok(user);
    }

    @Operation(summary = "新增用户")
    @PostMapping
    public Result<Void> add(@RequestBody SysUser user) {
        // TODO: 密码加密
        userService.save(user);
        return Result.ok();
    }

    @Operation(summary = "修改用户")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody SysUser user) {
        user.setId(id);
        user.setPassword(null); // 不允许通过此接口修改密码
        userService.updateById(user);
        return Result.ok();
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        userService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "导出用户")
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        List<SysUser> list = userService.list();
        list.forEach(user -> user.setPassword(null));
        ExcelUtils.export(response, "用户数据", SysUser.class, list);
    }
}
