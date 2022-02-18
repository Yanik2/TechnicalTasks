package com.yan.game.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "levelname")
    private String levelname;

    @OneToMany(mappedBy = "level")
    private List<Result> results;

    public Level() {
    }

    public Level(Integer id, String levelname) {
        this.id = id;
//        this.results = results;
        this.levelname = levelname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }
}
