package com.zippit.zippitbackend.beans;

import com.zippit.zippitbackend.entities.Assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akash.mercer on 18-Jun-17.
 */
public class AssignmentBean extends StatusBean {

    private String assignmentId;

    private String assignmentTitle;

    private Long deadlineDate;

    private Integer marks;

    private Boolean active;

    private String subject;

    private String teacherId;

    private String subjectChapterId;

    private String subjectChapterName;

    private List<String> standardDivisionIds = new ArrayList<>();

    private List<String> standardDivisionNames = new ArrayList<>();

    private List<String> questionIds = new ArrayList<>();

    private List<String> assignmentTags = new ArrayList<>();

    private List<String> assignmentTagIds = new ArrayList<>();

    private List<QuestionBean> questionBeans = new ArrayList<>();

    private StudentAssignmentBean studentAssignmentBean;

    private Boolean solved = false;

    private Boolean expired = false;

    public AssignmentBean() {

    }

    public AssignmentBean(String assignmentId, String assignmentTitle, Long deadlineDate, Integer marks, String subject, String subjectChapterName, String teacherId) {
        this.assignmentId = assignmentId;
        this.assignmentTitle = assignmentTitle;
        this.deadlineDate = deadlineDate;
        this.marks = marks;
        this.subject = subject;
        this.subjectChapterName = subjectChapterName;
        this.teacherId = teacherId;
    }

    public AssignmentBean(String assignmentId, String assignmentTitle, Long deadlineDate, Integer marks, String subject, String teacherId) {
        this.assignmentId = assignmentId;
        this.assignmentTitle = assignmentTitle;
        this.deadlineDate = deadlineDate;
        this.marks = marks;
        this.subject = subject;
        this.teacherId = teacherId;
    }

    public AssignmentBean(Assignment assignment) {
        assignmentId = assignment.getId();
        assignmentTitle = assignment.getAssignmentTitle();
        deadlineDate = assignment.getDeadlineDate();
        marks = assignment.getMarks();
        subject = assignment.getSubjectChapter().getSubject().getName();
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public Long getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Long deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubjectChapterId() {
        return subjectChapterId;
    }

    public void setSubjectChapterId(String subjectChapterId) {
        this.subjectChapterId = subjectChapterId;
    }

    public String getSubjectChapterName() {
        return subjectChapterName;
    }

    public void setSubjectChapterName(String subjectChapterName) {
        this.subjectChapterName = subjectChapterName;
    }

    public List<String> getStandardDivisionIds() {
        return standardDivisionIds;
    }

    public void setStandardDivisionIds(List<String> standardDivisionIds) {
        this.standardDivisionIds = standardDivisionIds;
    }

    public List<String> getStandardDivisionNames() {
        return standardDivisionNames;
    }

    public void setStandardDivisionNames(List<String> standardDivisionNames) {
        this.standardDivisionNames = standardDivisionNames;
    }

    public List<String> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<String> questionIds) {
        this.questionIds = questionIds;
    }

    public List<String> getAssignmentTags() {
        return assignmentTags;
    }

    public void setAssignmentTags(List<String> assignmentTags) {
        this.assignmentTags = assignmentTags;
    }

    public List<String> getAssignmentTagIds() {
        return assignmentTagIds;
    }

    public void setAssignmentTagIds(List<String> assignmentTagIds) {
        this.assignmentTagIds = assignmentTagIds;
    }

    public List<QuestionBean> getQuestionBeans() {
        return questionBeans;
    }

    public void setQuestionBeans(List<QuestionBean> questionBeans) {
        this.questionBeans = questionBeans;
    }

    public StudentAssignmentBean getStudentAssignmentBean() {
        return studentAssignmentBean;
    }

    public void setStudentAssignmentBean(StudentAssignmentBean studentAssignmentBean) {
        this.studentAssignmentBean = studentAssignmentBean;
    }

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }
}
