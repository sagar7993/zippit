package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.beans.SolutionOptionBean;
import com.zippit.zippitbackend.entities.Assignment;
import com.zippit.zippitbackend.entities.AssignmentQuestion;
import com.zippit.zippitbackend.beans.QuestionBean;
import com.zippit.zippitbackend.repositories.AssignmentQuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by akash.mercer on 05-Jul-17.
 */

@Service
public class AssignmentQuestionService {
    private static final String TAG = "AssignmentQuestionService : ";

    @Autowired
    private AssignmentQuestionRepository assignmentQuestionRepository;

    @Autowired
    private SolutionOptionService solutionOptionService;

    private static Logger debugLogger = LoggerFactory.getLogger("debugLogs");

    private static Logger errorLogger = LoggerFactory.getLogger("errorLogs");

    public void saveAll(List<AssignmentQuestion> assignmentQuestions) {
        assignmentQuestionRepository.save(assignmentQuestions);
    }

    public List<AssignmentQuestion> fetchAll() {
        return (List<AssignmentQuestion>) assignmentQuestionRepository.findAll();
    }

    public List<QuestionBean> findByAssignment(String assignmentId) {
        List<QuestionBean> questionBeans = assignmentQuestionRepository.findByAssignment(assignmentId);

        List<String> questionIds = new ArrayList<>();

        Map<String, QuestionBean> questionBeanMap = new HashMap<>();

        for (QuestionBean questionBean : questionBeans) {
            questionIds.add(questionBean.getQuestionId());
            questionBeanMap.put(questionBean.getQuestionId(), questionBean);
        }

        List<SolutionOptionBean> solutionOptionBeans = solutionOptionService.findByQuestionIds(questionIds);

        for (SolutionOptionBean solutionOptionBean : solutionOptionBeans) {
            questionBeanMap.get(solutionOptionBean.getQuestionId()).getSolutionOptionBeans().add(solutionOptionBean);
        }

        return questionBeans;
    }

    public List<AssignmentQuestion> findByAssignment(Assignment assignment) {
        return assignmentQuestionRepository.findByAssignment(assignment);
    }
}
