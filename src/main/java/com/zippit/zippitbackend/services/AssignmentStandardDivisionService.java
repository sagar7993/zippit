package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.beans.AssignmentBean;
import com.zippit.zippitbackend.beans.StandardBean;
import com.zippit.zippitbackend.beans.SubjectChapterBean;
import com.zippit.zippitbackend.entities.*;
import com.zippit.zippitbackend.repositories.AssignmentStandardDivisionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by akash.mercer on 05-Jul-17.
 */

@Service
public class AssignmentStandardDivisionService {
    private static final String TAG = "AssignmentStandardDivisionService : ";

    @Autowired
    private AssignmentStandardDivisionRepository assignmentStandardDivisionRepository;

    private static Logger debugLogger = LoggerFactory.getLogger("debugLogs");

    private static Logger errorLogger = LoggerFactory.getLogger("errorLogs");

    public List<AssignmentStandardDivision> fetchAll() {
        return (List<AssignmentStandardDivision>) assignmentStandardDivisionRepository.findAll();
    }

    public void saveAll(List<AssignmentStandardDivision> assignmentStandardDivisions) {
        assignmentStandardDivisionRepository.save(assignmentStandardDivisions);
    }

    public Page<AssignmentBean> findByStandardDivisionAndSubjectChapter(StandardDivision standardDivision, SubjectChapter subjectChapter, Pageable pageable) {
        return assignmentStandardDivisionRepository.findByStandardDivisionAndSubjectChapter(standardDivision, subjectChapter, pageable);
    }

    @Deprecated
    public List<StandardBean> getStandardsFromAssignmentsByTeacher(User teacher) {
        return assignmentStandardDivisionRepository.getStandardsFromAssignmentsByTeacher(teacher);
    }

    public List<AssignmentStandardDivision> findByAssignmentId(String assignmentId) {
        return assignmentStandardDivisionRepository.findByAssignmentId(assignmentId);
    }

    public List<SubjectChapterBean> getSubjectsForStandard(User institute, Standard standard) {
        return assignmentStandardDivisionRepository.getSubjectsForStandard(institute, standard);
    }

    public Page<AssignmentBean> findByInstituteAndSubjectChapter(User institute, SubjectChapter subjectChapter, Pageable pageable) {
        return assignmentStandardDivisionRepository.findByInstituteAndSubjectChapter(institute, subjectChapter, pageable);
    }

}
