package com.erp.system.controller;

import com.erp.common.result.Result;
import com.erp.system.entity.SysUser;
import com.erp.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 认证控制器
 */
@Tag(name = "认证管理")
@RestController
@RequestMapping("/system/auth")
@RequiredArgsConstructor
public class SysAuthController {

    private final SysUserService userService;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, String> loginForm) {
        String username = loginForm.get("username");
        String password = loginForm.get("password");

        // 查询用户
        SysUser user = userService.getByUsername(username);
        if (user == null) {
            return Result.fail("用户名或密码错误");
        }

        // TODO: 验证密码（需要集成 Sa-Token）
        // TODO: 生成 Token

        Map<String, Object> data = new HashMap<>();
        data.put("token", "mock-token-" + System.currentTimeMillis());
        data.put("userId", user.getId());
        data.put("username", user.getUsername());
        data.put("nickname", user.getNickname());

        return Result.ok(data, "登录成功");
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/info")
    public Result<Map<String, Object>> getInfo() {
        // TODO: 从 Token 中获取用户 ID
        Map<String, Object> data = new HashMap<>();
        data.put("user", new HashMap<>());
        data.put("roles", new String[]{"admin"});
        data.put("permissions", new String[]{"*:*:*"});
        return Result.ok(data);
    }

    @Operation(summary = "退出登录")
    @PostMapping("/logout")
    public Result<Void> logout() {
        // TODO: 清除 Token
        return Result.ok();
    }
}
