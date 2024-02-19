package com.rabbit.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveVo {
    private Long id;

    private String content;

    private Long categoryId;

    private String categoryName;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createdataTime;
}
