package com.zippit.zippitbackend.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akash.mercer on 05-Jul-17.
 */
public class QuestionBean {

    private String questionId;

    private String questionText;

    private String questionImageUrl;

    private Integer marks;

    private Boolean formula;

    private Integer questionType;

    private List<SolutionOptionBean> solutionOptionBeans = new ArrayList<>();

    private Boolean solved = false;

    public QuestionBean() {

    }

    public QuestionBean(String questionId, String questionText, String questionImageUrl, Integer marks, Boolean formula, Integer questionType) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.questionImageUrl = questionImageUrl;
        this.marks = marks;
        this.formula = formula;
        this.questionType = questionType;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionImageUrl() {
        return questionImageUrl;
    }

    public void setQuestionImageUrl(String questionImageUrl) {
        this.questionImageUrl = questionImageUrl;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public Boolean getFormula() {
        return formula;
    }

    public void setFormula(Boolean formula) {
        this.formula = formula;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public List<SolutionOptionBean> getSolutionOptionBeans() {
        return solutionOptionBeans;
    }

    public void setSolutionOptionBeans(List<SolutionOptionBean> solutionOptionBeans) {
        this.solutionOptionBeans = solutionOptionBeans;
    }

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }
}
