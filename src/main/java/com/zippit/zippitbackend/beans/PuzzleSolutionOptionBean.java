package com.zippit.zippitbackend.beans;

/**
 * Created by akash.mercer on 18-Jun-17.
 */
public class PuzzleSolutionOptionBean {

    private String puzzleSolutionOptionId;

    private String puzzleSolutionOptionText;

    private String puzzleSolutionOptionImageUrl;

    private Boolean solution;

    public PuzzleSolutionOptionBean() {

    }

    public String getPuzzleSolutionOptionId() {
        return puzzleSolutionOptionId;
    }

    public void setPuzzleSolutionOptionId(String puzzleSolutionOptionId) {
        this.puzzleSolutionOptionId = puzzleSolutionOptionId;
    }

    public String getPuzzleSolutionOptionText() {
        return puzzleSolutionOptionText;
    }

    public void setPuzzleSolutionOptionText(String puzzleSolutionOptionText) {
        this.puzzleSolutionOptionText = puzzleSolutionOptionText;
    }

    public String getPuzzleSolutionOptionImageUrl() {
        return puzzleSolutionOptionImageUrl;
    }

    public void setPuzzleSolutionOptionImageUrl(String puzzleSolutionOptionImageUrl) {
        this.puzzleSolutionOptionImageUrl = puzzleSolutionOptionImageUrl;
    }

    public Boolean getSolution() {
        return solution;
    }

    public void setSolution(Boolean solution) {
        this.solution = solution;
    }
}
