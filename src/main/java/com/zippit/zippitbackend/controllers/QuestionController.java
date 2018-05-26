package com.zippit.zippitbackend.controllers;

import com.zippit.zippitbackend.beans.GetQuestionBankBean;
import com.zippit.zippitbackend.beans.QuestionBean;
import com.zippit.zippitbackend.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by akash.mercer on 10-Jul-17.
 */

@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/getQuestions", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<QuestionBean> getQuestions(@RequestBody GetQuestionBankBean getQuestionBankBean) {
        return questionService.getQuestions(getQuestionBankBean);
    }

}
