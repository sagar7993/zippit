package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.beans.QuestionSolutionSummaryBean;
import com.zippit.zippitbackend.beans.StudentAssignmentQuestionBean;
import com.zippit.zippitbackend.entities.Assignment;
import com.zippit.zippitbackend.entities.StudentAssignmentQuestion;
import com.zippit.zippitbackend.repositories.StudentAssignmentQuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by akash.mercer on 06-Aug-17.
 */

@Service
public class StudentAssignmentQuestionService {
    private static final String TAG = "StudentAssignmentQuestionService : ";

    @Autowired
    private StudentAssignmentQuestionRepository studentAssignmentQuestionRepository;

    private static Logger debugLogger = LoggerFactory.getLogger("debugLogs");

    private static Logger errorLogger = LoggerFactory.getLogger("errorLogs");

    public void saveAll(List<StudentAssignmentQuestion> studentAssignmentQuestions) throws Exception {
        studentAssignmentQuestionRepository.save(studentAssignmentQuestions);
    }

    public List<QuestionSolutionSummaryBean> getAssignmentSummary(Assignment assignment) {
        List<StudentAssignmentQuestionBean> studentAssignmentQuestionBeans = studentAssignmentQuestionRepository.findByAssignment(assignment);

        Map<String, QuestionSolutionSummaryBean> questionSolutionSummaryBeanMap = new HashMap<>();

        List<QuestionSolutionSummaryBean> questionSolutionSummaryBeans = new ArrayList<>();

        for (StudentAssignmentQuestionBean studentAssignmentQuestionBean : studentAssignmentQuestionBeans) {
            String key = studentAssignmentQuestionBean.getQuestionId();

            if (questionSolutionSummaryBeanMap.containsKey(key)) {
                QuestionSolutionSummaryBean questionSolutionSummaryBean = questionSolutionSummaryBeanMap.get(key);
                questionSolutionSummaryBean.setAttempted(questionSolutionSummaryBean.getAttempted() + (studentAssignmentQuestionBean.getAttempted() ? 1 : 0));
                questionSolutionSummaryBean.setCorrect(questionSolutionSummaryBean.getCorrect() + (studentAssignmentQuestionBean.getCorrect() ? 1 : 0));
            } else {
                QuestionSolutionSummaryBean questionSolutionSummaryBean = new QuestionSolutionSummaryBean();
                questionSolutionSummaryBean.setQuestionText(studentAssignmentQuestionBean.getQuestionText());
                questionSolutionSummaryBean.setFormula(studentAssignmentQuestionBean.getFormula());
                questionSolutionSummaryBean.setTotal(assignment.getTotal());
                questionSolutionSummaryBean.setAttempted(studentAssignmentQuestionBean.getAttempted() ? 1 : 0);
                questionSolutionSummaryBean.setCorrect(studentAssignmentQuestionBean.getCorrect() ? 1 : 0);

                questionSolutionSummaryBeanMap.put(key, questionSolutionSummaryBean);

                questionSolutionSummaryBeans.add(questionSolutionSummaryBean);
            }
        }

        return questionSolutionSummaryBeans;
    }

    public List<StudentAssignmentQuestion> findByAssignmentId(String assignmentId) {
        return studentAssignmentQuestionRepository.findByAssignmentId(assignmentId);
    }

}
