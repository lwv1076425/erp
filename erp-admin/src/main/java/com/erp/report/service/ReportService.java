package com.erp.report.service;

import com.erp.finance.entity.FinPayment;
import com.erp.finance.service.FinPaymentService;
import com.erp.inventory.entity.InvStock;
import com.erp.inventory.service.InvStockService;
import com.erp.purchase.entity.PurOrder;
import com.erp.purchase.service.PurOrderService;
import com.erp.sales.entity.SalOrder;
import com.erp.sales.service.SalOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 报表服务
 */
@Service
@RequiredArgsConstructor
public class ReportService {

    private final SalOrderService salOrderService;
    private final PurOrderService purOrderService;
    private final InvStockService invStockService;
    private final FinPaymentService finPaymentService;

    /**
     * 获取仪表盘统计数据
     */
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();

        // 今日销售订单数
        LocalDate today = LocalDate.now();
        LocalDateTime todayStart = LocalDateTime.of(today, LocalTime.MIN);
        LocalDateTime todayEnd = LocalDateTime.of(today, LocalTime.MAX);

        // 销售统计
        long todaySalesCount = salOrderService.count();
        stats.put("todaySalesCount", todaySalesCount);

        // 采购统计
        long todayPurchaseCount = purOrderService.count();
        stats.put("todayPurchaseCount", todayPurchaseCount);

        // 库存预警（暂用总库存数代替）
        long totalStock = invStockService.count();
        stats.put("totalStock", totalStock);

        // 待处理订单
        long pendingOrders = salOrderService.count();
        stats.put("pendingOrders", pendingOrders);

        return stats;
    }

    /**
     * 获取销售趋势数据
     */
    public Map<String, Object> getSalesTrend() {
        Map<String, Object> result = new HashMap<>();
        // 简单返回模拟数据，实际应按月统计
        result.put("months", new String[]{"1月", "2月", "3月", "4月", "5月", "6月"});
        result.put("values", new BigDecimal[]{new BigDecimal("15000"), new BigDecimal("23000"),
                new BigDecimal("22400"), new BigDecimal("21800"), new BigDecimal("13500"), new BigDecimal("14700")});
        return result;
    }
}
