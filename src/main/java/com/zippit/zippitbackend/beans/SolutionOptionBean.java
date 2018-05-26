package com.zippit.zippitbackend.beans;

/**
 * Created by akash.mercer on 05-Jul-17.
 */
public class SolutionOptionBean {

    private String solutionOptionId;

    private String solutionOptionText;

    private String solutionOptionImageUrl;

    private Boolean solution;

    private Boolean formula;

    private Boolean selectedSolution = false;

    private String questionId;

    private Integer marks;

    public SolutionOptionBean() {

    }

    public SolutionOptionBean(String solutionOptionId, String solutionOptionText, String solutionOptionImageUrl, Boolean solution, Boolean formula, String questionId, Integer marks) {
        this.solutionOptionId = solutionOptionId;
        this.solutionOptionText = solutionOptionText;
        this.solutionOptionImageUrl = solutionOptionImageUrl;
        this.solution = solution;
        this.formula = formula;
        this.questionId = questionId;
        this.marks = marks;
    }

    public String getSolutionOptionId() {
        return solutionOptionId;
    }

    public void setSolutionOptionId(String solutionOptionId) {
        this.solutionOptionId = solutionOptionId;
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

    public Boolean getSelectedSolution() {
        return selectedSolution;
    }

    public void setSelectedSolution(Boolean selectedSolution) {
        this.selectedSolution = selectedSolution;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }
}
