package com.zippit.zippitbackend.controllers;

import com.zippit.zippitbackend.beans.*;
import com.zippit.zippitbackend.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * Created by akash.mercer on 04-Jun-17.
 */

@RestController
@RequestMapping(value = "/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @RequestMapping(value = "/findByUser/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    AssignmentsListBean findByUser(@PathVariable("userId") String userId) {
        return assignmentService.findByUser(userId);
    }

    @RequestMapping(value = "/findByAssignment/{assignmentId}", method = RequestMethod.GET)
    public @ResponseBody AssignmentBean findByAssignment(@PathVariable("assignmentId") String assignmentId) {
        return assignmentService.findByAssignment(assignmentId);
    }

    @RequestMapping(value = "/findByUserAndSubjectChapter/{userId}/{subjectChapterId}", method = RequestMethod.GET)
    public @ResponseBody AssignmentsBean findByUserAndSubjectChapter(@PathVariable("userId") String userId, @PathVariable("subjectChapterId") String subjectChapterId, @PageableDefault(size = 20, sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return assignmentService.findByUserAndSubjectChapter(userId, subjectChapterId, pageable);
    }

    @RequestMapping(value = "/findById/{userId}/{assignmentId}", method = RequestMethod.GET)
    public @ResponseBody
    AssignmentDetailsBean findById(@PathVariable("userId") String userId, @PathVariable("assignmentId") String assignmentId) {
        return assignmentService.findById(userId, assignmentId);
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    StatusBean submit(@RequestBody SubmitAssignmentBean submitAssignmentBean) {
        return assignmentService.submit(submitAssignmentBean);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody StatusBean save(@RequestBody AssignmentBean assignmentBean) {
        return assignmentService.save(assignmentBean);
    }

    @RequestMapping(value = "/getAssignmentSummary/{assignmentId}", method = RequestMethod.GET)
    public @ResponseBody
    TeacherAssignmentSummaryBean getAssignmentSummary(@PathVariable("assignmentId") String assignmentId) {
        return assignmentService.getAssignmentSummary(assignmentId);
    }

}
