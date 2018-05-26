package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.entities.Standard;
import com.zippit.zippitbackend.repositories.StandardRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akash.mercer on 04-Jun-17.
 */

@Service
public class StandardService {
    private static final String TAG = "StandardService : ";

    @Autowired
    private StandardRepository standardRepository;

    private static Logger debugLogger = Logger.getLogger("debugLogs");

    private static Logger errorLogger = Logger.getLogger("errorLogs");

    public Integer getCount() {
        return standardRepository.getCount();
    }

    public Standard findById(String id) {
        return standardRepository.findOne(id);
    }

    public Standard findByStandardName(Integer standardName) {
        return standardRepository.findByStandardName(standardName);
    }
    
}
