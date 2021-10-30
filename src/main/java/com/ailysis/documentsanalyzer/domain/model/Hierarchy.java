package com.ailysis.documentsanalyzer.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hierarchy")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hierarchy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "category_base_hierarchy",
            joinColumns = @JoinColumn(name = "hierarchy_id"),
            inverseJoinColumns = @JoinColumn(name = "category_base_id")
    )
    @JsonIgnore
    private List<CategoryBase> categoryBases;
}
