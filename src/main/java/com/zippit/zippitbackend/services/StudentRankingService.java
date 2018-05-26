package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.entities.StudentRanking;
import com.zippit.zippitbackend.repositories.StudentRankingRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akash.mercer on 08-Jun-17.
 */

@Service
public class StudentRankingService {
    private static final String TAG = "StudentRankingService : ";

    @Autowired
    private StudentRankingRepository studentRankingRepository;

    private static Logger debugLogger = Logger.getLogger("debugLogs");

    private static Logger errorLogger = Logger.getLogger("errorLogs");

    public void save(StudentRanking studentRanking) {
        studentRankingRepository.save(studentRanking);
    }
}
