package com.zippit.zippitbackend.beans;

import org.springframework.data.domain.Page;

/**
 * Created by akash.mercer on 06-Jul-17.
 */

public class AssignmentsBean extends StatusBean {

    private Page<AssignmentBean> assignmentsPage;

    public AssignmentsBean() {

    }

    public Page<AssignmentBean> getAssignmentsPage() {
        return assignmentsPage;
    }

    public void setAssignmentsPage(Page<AssignmentBean> assignmentsPage) {
        this.assignmentsPage = assignmentsPage;
    }
}
