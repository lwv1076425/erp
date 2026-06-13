package com.erp.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.base.entity.BaseCustomer;
import com.erp.base.service.BaseCustomerService;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 客户管理控制器
 */
@Tag(name = "客户管理")
@RestController
@RequestMapping("/base/customer")
@RequiredArgsConstructor
public class BaseCustomerController {

    private final BaseCustomerService customerService;

    @Operation(summary = "客户列表")
    @GetMapping
    public Result<PageResult<BaseCustomer>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize,
            @RequestParam(required = false) String customerName) {

        LambdaQueryWrapper<BaseCustomer> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(customerName), BaseCustomer::getCustomerName, customerName);
        wrapper.orderByDesc(BaseCustomer::getCreateTime);

        Page<BaseCustomer> page = customerService.page(new Page<>(pageNum, pageSize), wrapper);
        return Result.ok(PageResult.of(page));
    }

    @Operation(summary = "客户详情")
    @GetMapping("/{id}")
    public Result<BaseCustomer> getInfo(@PathVariable Long id) {
        return Result.ok(customerService.getById(id));
    }

    @Operation(summary = "新增客户")
    @PostMapping
    public Result<Void> add(@RequestBody BaseCustomer customer) {
        customerService.save(customer);
        return Result.ok();
    }

    @Operation(summary = "修改客户")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody BaseCustomer customer) {
        customer.setId(id);
        customerService.updateById(customer);
        return Result.ok();
    }

    @Operation(summary = "删除客户")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        customerService.removeById(id);
        return Result.ok();
    }
}
