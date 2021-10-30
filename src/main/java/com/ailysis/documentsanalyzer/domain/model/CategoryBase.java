package com.ailysis.documentsanalyzer.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category_base")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryBase {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "label")
    private String label;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "category_base_hierarchy",
            joinColumns = @JoinColumn(name = "category_base_id"),
            inverseJoinColumns = @JoinColumn(name = "hierarchy_id")
            )
    private List<Hierarchy> hierarchies;

    @OneToMany(mappedBy = "categoryBase")
    @JsonIgnore
    private List<Category> categories;

    public void addCategory(Category category) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        categories.add(category);
        category.setCategoryBase(this);
    }

    public void addHierarchy(Hierarchy hierarchy) {
        if (hierarchies == null) {
            hierarchies = new ArrayList<>();
        }
        hierarchies.add(hierarchy);
    }


}
