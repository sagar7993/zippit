package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.beans.SolutionOptionBean;
import com.zippit.zippitbackend.entities.SolutionOption;
import com.zippit.zippitbackend.repositories.SolutionOptionRepository;
import com.zippit.zippitbackend.entities.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by akash.mercer on 05-Jul-17.
 */

@Service
public class SolutionOptionService {
    private static final String TAG = "SolutionOptionService : ";

    @Autowired
    private SolutionOptionRepository solutionOptionRepository;

    private static Logger debugLogger = LoggerFactory.getLogger("debugLogs");

    private static Logger errorLogger = LoggerFactory.getLogger("errorLogs");

    public List<SolutionOptionBean> findByQuestionIds(List<String> questionIds) {
        return solutionOptionRepository.findByQuestionIds(questionIds);
    }

    public List<SolutionOptionBean> findByQuestionIdsWithSolution(List<String> questionIds) {
        return solutionOptionRepository.findByQuestionIdsWithSolution(questionIds);
    }

    public void saveAll(List<SolutionOption> solutionOptions) {
        solutionOptionRepository.save(solutionOptions);
    }

    public List<SolutionOption> findByQuestion(Question question) {
        return solutionOptionRepository.findByQuestion(question);
    }

    public List<SolutionOptionBean> findByQuestionId(String questionId) {
        return solutionOptionRepository.findByQuestionId(questionId);
    }
}
