package com.ailysis.documentsanalyzer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentRequestDto {

    private String title;
    private String content;
    private String path;
    private String size;


}
