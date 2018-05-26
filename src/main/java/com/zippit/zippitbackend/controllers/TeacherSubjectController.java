package com.zippit.zippitbackend.controllers;

import com.zippit.zippitbackend.beans.StandardsBean;
import com.zippit.zippitbackend.beans.SubjectsBean;
import com.zippit.zippitbackend.services.TeacherSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by akash.mercer on 10-Aug-17.
 */

@RestController
@RequestMapping(value = "/teacherSubject")
public class TeacherSubjectController {

    @Autowired
    private TeacherSubjectService teacherSubjectService;

    @RequestMapping(value = "/getStandards/{teacherId}", method = RequestMethod.GET)
    public @ResponseBody
    StandardsBean getStandards(@PathVariable("teacherId") String teacherId){
        return teacherSubjectService.getStandards(teacherId);
    }

    @RequestMapping(value = "/findByStandards/{teacherId}/{standardId}", method = RequestMethod.GET)
    public @ResponseBody SubjectsBean findByStandards(@PathVariable("teacherId") String teacherId, @PathVariable("standardId") String standardId){
        return teacherSubjectService.findByStandards(teacherId, standardId);
    }
}
