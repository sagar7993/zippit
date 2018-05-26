package com.zippit.zippitbackend.controllers;

import com.zippit.zippitbackend.beans.StudentAssignmentSummaryBean;
import com.zippit.zippitbackend.services.StudentAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by akash.mercer on 11-Jul-17.
 */

@RestController
@RequestMapping(value = "/studentAssignment")
public class StudentAssignmentController {

    @Autowired
    private StudentAssignmentService studentAssignmentService;

    @RequestMapping(value = "/findByAssignment/{studentId}/{assignmentId}", method = RequestMethod.GET)
    public @ResponseBody
    StudentAssignmentSummaryBean findByAssignment(@PathVariable("studentId") String studentId, @PathVariable("assignmentId") String assignmentId) {
        return studentAssignmentService.findByStudentAndAssignment(studentId, assignmentId);
    }
}
