package com.erp.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.base.entity.BaseProduct;
import com.erp.base.service.BaseProductService;
import com.erp.common.result.PageResult;
import com.erp.common.result.Result;
import com.erp.common.utils.ExcelUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品管理控制器
 */
@Tag(name = "商品管理")
@RestController
@RequestMapping("/base/product")
@RequiredArgsConstructor
public class BaseProductController {

    private final BaseProductService productService;

    @Operation(summary = "商品列表")
    @GetMapping
    public Result<PageResult<BaseProduct>> list(
            @RequestParam(defaultValue = "1") long pageNum,
            @RequestParam(defaultValue = "10") long pageSize,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) Long categoryId) {

        LambdaQueryWrapper<BaseProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(productName), BaseProduct::getProductName, productName);
        wrapper.eq(categoryId != null, BaseProduct::getCategoryId, categoryId);
        wrapper.orderByDesc(BaseProduct::getCreateTime);

        Page<BaseProduct> page = productService.page(new Page<>(pageNum, pageSize), wrapper);
        return Result.ok(PageResult.of(page));
    }

    @Operation(summary = "商品详情")
    @GetMapping("/{id}")
    public Result<BaseProduct> getInfo(@PathVariable Long id) {
        return Result.ok(productService.getById(id));
    }

    @Operation(summary = "新增商品")
    @PostMapping
    public Result<Void> add(@RequestBody BaseProduct product) {
        productService.save(product);
        return Result.ok();
    }

    @Operation(summary = "修改商品")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody BaseProduct product) {
        product.setId(id);
        productService.updateById(product);
        return Result.ok();
    }

    @Operation(summary = "删除商品")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        productService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "导出商品")
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        List<BaseProduct> list = productService.list();
        ExcelUtils.export(response, "商品数据", BaseProduct.class, list);
    }
}
