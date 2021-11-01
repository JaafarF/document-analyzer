package com.ailysis.documentsanalyzer.domain.dto;

import com.ailysis.documentsanalyzer.domain.model.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private long id;
    private String categoryBaseId;
    private String namespace;
    private String label;
    private String hierarchy;
    private int score;
    private Float frequency;
    private Boolean winner;
    private List<Position> positions;

}
