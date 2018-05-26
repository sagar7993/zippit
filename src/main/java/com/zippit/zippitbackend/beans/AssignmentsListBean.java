package com.zippit.zippitbackend.beans;

import java.util.List;

/**
 * Created by Sagar Jain on 8/6/2017.
 */

public class AssignmentsListBean extends StatusBean {

    private List<AssignmentBean> assignmentsList;

    public AssignmentsListBean() {

    }

    public List<AssignmentBean> getAssignmentsList() {
        return assignmentsList;
    }

    public void setAssignmentsList(List<AssignmentBean> assignmentsList) {
        this.assignmentsList = assignmentsList;
    }
}
