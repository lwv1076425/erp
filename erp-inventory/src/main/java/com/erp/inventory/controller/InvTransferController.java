package com.erp.inventory.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import com.erp.common.utils.BizUtils;
import com.erp.inventory.entity.InvTransfer;
import com.erp.inventory.service.InvTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "库存调拨")
@RestController
@RequestMapping("/inventory/transfer")
@RequiredArgsConstructor
public class InvTransferController {

    private final InvTransferService transferService;

    @Operation(summary = "调拨列表")
    @GetMapping
    public Result<PageResult<InvTransfer>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize) {
        LambdaQueryWrapper<InvTransfer> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(InvTransfer::getCreateTime);
        return Result.ok(PageResult.of(transferService.page(new Page<>(pageNum, pageSize), wrapper)));
    }

    @Operation(summary = "新增调拨")
    @PostMapping
    public Result<Void> add(@RequestBody InvTransfer transfer) {
        if (transfer.getTransferNo() == null) {
            transfer.setTransferNo(BizUtils.generateOrderNo("TF"));
        }
        transferService.save(transfer);
        return Result.ok();
    }

    @Operation(summary = "删除调拨")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        transferService.removeById(id);
        return Result.ok();
    }
}
