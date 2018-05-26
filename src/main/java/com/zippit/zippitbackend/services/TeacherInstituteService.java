package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.entities.TeacherInstitute;
import com.zippit.zippitbackend.entities.User;
import com.zippit.zippitbackend.repositories.TeacherInstituteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by akash.mercer on 04-Jun-17.
 */

@Service
public class TeacherInstituteService {
    private static final String TAG = "TeacherInstituteService : ";

    @Autowired
    private TeacherInstituteRepository teacherInstituteRepository;

    private static Logger debugLogger = Logger.getLogger("debugLogs");

    private static Logger errorLogger = Logger.getLogger("errorLogs");

    public void save(TeacherInstitute teacherInstitute) throws Exception {
        teacherInstituteRepository.save(teacherInstitute);
    }

    public void saveAll(List<TeacherInstitute> teacherInstitutes) throws Exception {
        teacherInstituteRepository.save(teacherInstitutes);
    }

    public TeacherInstitute findByTeacher(User teacher) {
        return teacherInstituteRepository.findByTeacher(teacher);
    }
}
