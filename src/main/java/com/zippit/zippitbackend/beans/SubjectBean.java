package com.zippit.zippitbackend.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akash.mercer on 05-Jul-17.
 */
public class SubjectBean {

    private String subjectId;

    private String name;

    private Integer type;

    private Boolean active;

    private List<SubjectChapterBean> subjectChapterBeans = new ArrayList<>();

    public SubjectBean() {

    }

    public SubjectBean(String subjectId, String name, Integer type) {
        this.subjectId = subjectId;
        this.name = name;
        this.type = type;
    }

    public SubjectBean(String subjectId, String name, Integer type, Boolean active) {
        this.subjectId = subjectId;
        this.name = name;
        this.type = type;
        this.active = active;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<SubjectChapterBean> getSubjectChapterBeans() {
        return subjectChapterBeans;
    }

    public void setSubjectChapterBeans(List<SubjectChapterBean> subjectChapterBeans) {
        this.subjectChapterBeans = subjectChapterBeans;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
