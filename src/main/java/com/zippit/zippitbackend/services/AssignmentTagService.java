package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.repositories.AssignmentTagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akash.mercer on 05-Jun-17.
 */

@Service
public class AssignmentTagService {
    private static final String TAG = "AssignmentTagService : ";

    @Autowired
    private AssignmentTagRepository assignmentTagRepository;

    private static Logger debugLogger = LoggerFactory.getLogger("debugLogs");

    private static Logger errorLogger = LoggerFactory.getLogger("errorLogs");

}
