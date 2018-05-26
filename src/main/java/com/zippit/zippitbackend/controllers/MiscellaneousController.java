package com.zippit.zippitbackend.controllers;

import com.zippit.zippitbackend.beans.StatusBean;
import com.zippit.zippitbackend.services.MiscellaneousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by akash.mercer on 06-Aug-17.
 */

@RestController
@RequestMapping(value = "/miscellaneous")
public class MiscellaneousController {

    @Autowired
    private MiscellaneousService miscellaneousService;

    @Deprecated
    @RequestMapping(value = "/populateAssignmentTotal", method = RequestMethod.GET)
    public @ResponseBody StatusBean populateAssignmentTotal() {
        return miscellaneousService.populateAssignmentTotal();
    }

    @Deprecated
    @RequestMapping(value = "/populateStudentAssignmentDates", method = RequestMethod.GET)
    public @ResponseBody StatusBean populateStudentAssignmentDates() {
        return miscellaneousService.populateStudentAssignmentDates();
    }

    @Deprecated
    @RequestMapping(value = "/populateStudentAssignmentQuestions", method = RequestMethod.GET)
    public @ResponseBody StatusBean populateStudentAssignmentQuestions() throws Exception {
        return miscellaneousService.populateStudentAssignmentQuestions();
    }
}
