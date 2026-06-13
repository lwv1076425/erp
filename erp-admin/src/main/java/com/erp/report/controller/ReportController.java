package com.erp.report.controller;

import com.erp.common.result.Result;
import com.erp.report.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 报表控制器
 */
@Tag(name = "报表管理")
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "仪表盘统计")
    @GetMapping("/dashboard")
    public Result<Map<String, Object>> getDashboardStats() {
        return Result.ok(reportService.getDashboardStats());
    }

    @Operation(summary = "销售趋势")
    @GetMapping("/sales-trend")
    public Result<Map<String, Object>> getSalesTrend() {
        return Result.ok(reportService.getSalesTrend());
    }
}
