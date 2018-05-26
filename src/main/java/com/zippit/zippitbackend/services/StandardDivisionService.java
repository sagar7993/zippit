package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.beans.StandardBean;
import com.zippit.zippitbackend.entities.Standard;
import com.zippit.zippitbackend.entities.StandardDivision;
import com.zippit.zippitbackend.entities.User;
import com.zippit.zippitbackend.repositories.StandardDivisionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akash.mercer on 07-Jul-17.
 */

@Service
public class StandardDivisionService {
    private static final String TAG = "StandardDivisionService : ";

    @Autowired
    private StandardDivisionRepository standardDivisionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private StandardService standardService;

    private static Logger debugLogger = LoggerFactory.getLogger("debugLogs");

    private static Logger errorLogger = LoggerFactory.getLogger("errorLogs");

    public StandardDivision findById(String id) {
        return standardDivisionRepository.findOne(id);
    }

    public List<StandardDivision> findByInstitute(String instituteId) {
        User retrievedInstitute = userService.findById(instituteId);

        if (retrievedInstitute == null) {
            errorLogger.error(TAG + "Error in finding Institute with id : " + instituteId);
            return null;
        }

        return standardDivisionRepository.findByInstitute(retrievedInstitute);
    }

    public List<StandardDivision> findByIds(List<String> standardIds) {
        return standardDivisionRepository.findByIds(standardIds);
    }

    public void saveStandardDivisions(String instituteId, List<StandardBean> standardBeans) throws Exception {
        User retrievedInstitute = userService.findById(instituteId);

        List<StandardDivision> standardDivisions = new ArrayList<>();

        for (StandardBean standardBean : standardBeans) {
            Standard retrievedStandard = standardService.findById(standardBean.getStandardId());

            for (String divisionName : standardBean.getDivisionNames()) {
                standardDivisions.add(new StandardDivision(divisionName, retrievedInstitute, retrievedStandard));
            }
        }

        standardDivisionRepository.save(standardDivisions);

    }
}
