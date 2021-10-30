package com.ailysis.documentsanalyzer.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "score")
    private int score;

    @Column(name = "winner")
    private Boolean winner;

    @Column(name = "namespace")
    private String namespace;

    @Column(name = "frequency")
    private Float frequency;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private List<Position> positions;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_base_id")
    private CategoryBase categoryBase;

    @ManyToOne
    @JoinColumn(name = "document_id")
    @JsonIgnore
    private Document document;

    public void addPosition(Position position) {
        if (positions == null) {
            positions = new ArrayList<>();
        }
        positions.add(position);
    }

}
