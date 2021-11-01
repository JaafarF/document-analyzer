package com.ailysis.documentsanalyzer.domain.dto;

import ai.expert.nlapi.v2.API;
import com.ailysis.documentsanalyzer.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResponseDto implements Serializable {

    private Long id;
    private String title;
    private String content;
    private String path;
    private String size;
    private API.Languages language;
    private String version;
    private List<CategoryDto> categories;

}
