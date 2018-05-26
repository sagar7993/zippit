package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.repositories.StudentParentRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akash.mercer on 05-Jun-17.
 */

@Service
public class StudentParentService {
    private static final String TAG = "StudentParentService : ";

    @Autowired
    private StudentParentRepository studentParentRepository;

    private static Logger debugLogger = Logger.getLogger("debugLogs");

    private static Logger errorLogger = Logger.getLogger("errorLogs");

}
