package com.zippit.zippitbackend.beans;

/**
 * Created by Sagar Jain on 7/23/2017.
 */
public class GetQuestionBankBean {

    private String standardDivisionId;

    private String subjectChapterId;

    private String boardId;

    public String getStandardDivisionId() {
        return standardDivisionId;
    }

    public void setStandardDivisionId(String standardDivisionId) {
        this.standardDivisionId = standardDivisionId;
    }

    public String getSubjectChapterId() {
        return subjectChapterId;
    }

    public void setSubjectChapterId(String subjectChapterId) {
        this.subjectChapterId = subjectChapterId;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
    
}
