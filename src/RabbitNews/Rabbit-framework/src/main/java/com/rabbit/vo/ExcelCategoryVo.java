package com.rabbit.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelCategoryVo {
    @ExcelProperty("分类名")
    private Long id;
    @ExcelProperty("描述")
    private String name;

    //状态0:正常，1禁用
    @ExcelProperty("状态0:正常，1禁用")
    private String status;

    private String description;
}
