package com.zippit.zippitbackend.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by akash.mercer on 05-Jul-17.
 */

@Entity
@Table(name = "solution_option")
public class SolutionOption {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 60)
    private String id;

    @Column(nullable = false)
    private String solutionOptionText;

    @Column
    private String solutionOptionImageUrl;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean solution = false;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean formula = false;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question", nullable = false)
    private Question question;

    public SolutionOption() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSolutionOptionText() {
        return solutionOptionText;
    }

    public void setSolutionOptionText(String solutionOptionText) {
        this.solutionOptionText = solutionOptionText;
    }

    public String getSolutionOptionImageUrl() {
        return solutionOptionImageUrl;
    }

    public void setSolutionOptionImageUrl(String solutionOptionImageUrl) {
        this.solutionOptionImageUrl = solutionOptionImageUrl;
    }

    public Boolean getSolution() {
        return solution;
    }

    public void setSolution(Boolean solution) {
        this.solution = solution;
    }

    public Boolean getFormula() {
        return formula;
    }

    public void setFormula(Boolean formula) {
        this.formula = formula;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
