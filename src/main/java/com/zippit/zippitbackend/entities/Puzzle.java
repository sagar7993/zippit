package com.zippit.zippitbackend.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by akash.mercer on 08-Jun-17.
 */

@Entity
@Table(name = "puzzle")
public class Puzzle {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 60)
    private String id;

    @Column(nullable = false)
    private String puzzleText;

    @Column
    private String puzzleImageUrl;

    @Column(nullable = false, columnDefinition = "int(11) default '0'")
    private Integer points = 0;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    public Puzzle() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPuzzleText() {
        return puzzleText;
    }

    public void setPuzzleText(String puzzleText) {
        this.puzzleText = puzzleText;
    }

    public String getPuzzleImageUrl() {
        return puzzleImageUrl;
    }

    public void setPuzzleImageUrl(String puzzleImageUrl) {
        this.puzzleImageUrl = puzzleImageUrl;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
