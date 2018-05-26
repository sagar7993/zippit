package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.entities.UserType;
import com.zippit.zippitbackend.repositories.UserTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by akash.mercer on 21-Jun-17.
 */

@Service
public class UserTypeService {
    private static final String TAG = "UserTypeService : ";

    @Autowired
    private UserTypeRepository userTypeRepository;

    private static Logger debugLogger = LoggerFactory.getLogger("debugLogs");

    private static Logger errorLogger = LoggerFactory.getLogger("errorLogs");

    public Integer getCount() {
        return userTypeRepository.getCount();
    }

    public UserType findByType(Integer type) {
        return userTypeRepository.findByType(type);
    }

    public List<UserType> findTeacherOrInstituteTypes() {
        return userTypeRepository.findTeacherOrInstituteTypes();
    }
}
