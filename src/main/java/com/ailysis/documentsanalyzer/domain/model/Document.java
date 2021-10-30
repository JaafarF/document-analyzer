package com.ailysis.documentsanalyzer.domain.model;

import ai.expert.nlapi.v2.API;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "document")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "initial_text")
    private String initialContent;

    @Column(name = "size")
    private Integer size;

    @Column(name = "language")
    @Enumerated(EnumType.ORDINAL)
    private API.Languages language;

    @Column(name = "version")
    private String version;

    @OneToMany(mappedBy = "document",
            cascade = {CascadeType.PERSIST})
    private List<Category> categories;

    public void addCategory(Category category) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        categories.add(category);
        category.setDocument(this);
    }

}