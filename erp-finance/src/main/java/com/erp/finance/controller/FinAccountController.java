package com.erp.finance.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import com.erp.finance.entity.FinAccount;
import com.erp.finance.service.FinAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "账户管理")
@RestController
@RequestMapping("/finance/account")
@RequiredArgsConstructor
public class FinAccountController {

    private final FinAccountService accountService;

    @Operation(summary = "账户列表")
    @GetMapping
    public Result<PageResult<FinAccount>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize) {
        LambdaQueryWrapper<FinAccount> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(FinAccount::getCreateTime);
        return Result.ok(PageResult.of(accountService.page(new Page<>(pageNum, pageSize), wrapper)));
    }

    @Operation(summary = "新增账户")
    @PostMapping
    public Result<Void> add(@RequestBody FinAccount account) {
        accountService.save(account);
        return Result.ok();
    }

    @Operation(summary = "修改账户")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody FinAccount account) {
        account.setId(id);
        accountService.updateById(account);
        return Result.ok();
    }

    @Operation(summary = "删除账户")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        accountService.removeById(id);
        return Result.ok();
    }
}
