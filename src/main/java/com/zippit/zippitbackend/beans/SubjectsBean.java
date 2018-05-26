package com.zippit.zippitbackend.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akash.mercer on 19-Jul-17.
 */
public class SubjectsBean extends StatusBean {

    private List<SubjectBean> subjectBeans = new ArrayList<>();

    public SubjectsBean() {

    }

    public List<SubjectBean> getSubjectBeans() {
        return subjectBeans;
    }

    public void setSubjectBeans(List<SubjectBean> subjectBeans) {
        this.subjectBeans = subjectBeans;
    }
}
