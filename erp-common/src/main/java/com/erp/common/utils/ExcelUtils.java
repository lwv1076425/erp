package com.erp.common.utils;

import com.alibaba.excel.EasyExcel;
import com.erp.common.exception.BusinessException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Excel 工具类
 */
@Slf4j
public class ExcelUtils {

    /**
     * 导出 Excel
     */
    public static <T> void export(HttpServletResponse response, String fileName, Class<T> clazz, List<T> data) {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + encodedFileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), clazz)
                    .sheet("Sheet1")
                    .doWrite(data);
        } catch (IOException e) {
            log.error("Excel 导出失败", e);
            throw new BusinessException("Excel 导出失败");
        }
    }
}
