package com.ailysis.documentsanalyzer.domain.dto;

import lombok.Data;

@Data
public class DocumentRequestDto {

    private String title;

    private String initialContent;

    private Integer size;


}
