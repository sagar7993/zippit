package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.beans.SubjectBean;
import com.zippit.zippitbackend.beans.SubjectChapterBean;
import com.zippit.zippitbackend.entities.User;
import com.zippit.zippitbackend.repositories.SubjectRepository;
import com.zippit.zippitbackend.utils.Constants;
import com.zippit.zippitbackend.beans.SubjectsBean;
import com.zippit.zippitbackend.entities.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by akash.mercer on 05-Jul-17.
 */

@Service
public class SubjectService {
    private static final String TAG = "SubjectService : ";

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private TeacherSubjectService teacherSubjectService;

    @Autowired
    private SubjectChapterService subjectChapterService;

    private static Logger debugLogger = LoggerFactory.getLogger("debugLogs");

    private static Logger errorLogger = LoggerFactory.getLogger("errorLogs");

    public Integer getCount() {
        return subjectRepository.getCount();
    }

    public Subject findById(String id) {
        return subjectRepository.findOne(id);
    }

    public Subject findByType(Integer type) {
        return subjectRepository.findByType(type);
    }

    public List<Subject> fetchAll() {
        return (List<Subject>) subjectRepository.findAll();
    }

    public SubjectsBean findAllSubjectBean() {
        SubjectsBean subjectsBean = new SubjectsBean();
        List<SubjectBean> subjectBeans = subjectRepository.findAllSubjectBean();
        subjectsBean.setSubjectBeans(subjectBeans);
        subjectsBean.setStatus(1);
        return subjectsBean;
    }

    public SubjectsBean findByTeacher(String teacherId) {
        SubjectsBean subjectsBean = new SubjectsBean();

        User retrievedTeacher = userService.findById(teacherId);

        if (retrievedTeacher == null) {
            errorLogger.error(TAG + "Error in finding Teacher with Id : " + teacherId);

            subjectsBean.setStatus(0);
            subjectsBean.setMessage(Constants.SOMETHING_WENT_WRONG);

            return subjectsBean;
        }

        List<SubjectBean> subjectBeans = teacherSubjectService.findByTeacher(retrievedTeacher);
        
        subjectsBean.setSubjectBeans(subjectBeans);

        subjectsBean.setStatus(1);
        return subjectsBean;
    }

    @Deprecated
    public SubjectsBean findByTeacherAndAssignments(String teacherId) {
        SubjectsBean subjectsBean = findByTeacher(teacherId);

        if (subjectsBean.getStatus() == 0) {
            return subjectsBean;
        }

        User retrievedTeacher = userService.findById(teacherId);

        List<SubjectChapterBean> subjectChapterBeans = assignmentService.findSubjectChaptersFromAssignmentsByTeacher(retrievedTeacher);

        Map<String, List<SubjectChapterBean>> subjectChapterMap = new HashMap<>();

        for (SubjectChapterBean subjectChapterBean : subjectChapterBeans) {
            String key = subjectChapterBean.getSubjectId();

            if (subjectChapterMap.containsKey(key)) {
                List<SubjectChapterBean> retrievedSubjectChapterBeans = subjectChapterMap.get(key);
                retrievedSubjectChapterBeans.add(subjectChapterBean);
            } else {
                List<SubjectChapterBean> newSubjectChapterBeans = new ArrayList<>();
                newSubjectChapterBeans.add(subjectChapterBean);
                subjectChapterMap.put(key, newSubjectChapterBeans);
            }
        }

        for (SubjectBean subjectBean : subjectsBean.getSubjectBeans()) {
            List<SubjectChapterBean> retrievedSubjectChapterBeans = subjectChapterMap.get(subjectBean.getSubjectId());

            if (!CollectionUtils.isEmpty(retrievedSubjectChapterBeans)) {
                subjectBean.setSubjectChapterBeans(retrievedSubjectChapterBeans);
            } else {
                subjectBean.getSubjectChapterBeans().add(new SubjectChapterBean("No chapters available for " + subjectBean.getName() + " subject."));
            }
        }

        subjectsBean.setStatus(1);
        return subjectsBean;
    }

    public SubjectsBean findByStudent(String studentId) {
        SubjectsBean subjectsBean = new SubjectsBean();

        User retrievedStudent = userService.findById(studentId);

        if (retrievedStudent == null) {
            errorLogger.error(TAG + "Error in finding Student with Id : " + studentId);

            subjectsBean.setStatus(0);
            subjectsBean.setMessage(Constants.SOMETHING_WENT_WRONG);

            return subjectsBean;
        }

        List<SubjectBean> subjectBeans = subjectRepository.findAllActiveSubjectBean();

        subjectsBean.setSubjectBeans(subjectBeans);

        List<String> subjectIds = new ArrayList<>();

        for (SubjectBean subjectBean : subjectBeans) {
            subjectIds.add(subjectBean.getSubjectId());
        }

        List<SubjectChapterBean> subjectChapterBeans = subjectChapterService.findBySubjectIdsAndBoardAndStandard(subjectIds, retrievedStudent.getStandardDivision().getBoard(), retrievedStudent.getStandardDivision().getStandard());

        Map<String, List<SubjectChapterBean>> subjectChapterMap = new HashMap<>();

        for (SubjectChapterBean subjectChapterBean : subjectChapterBeans) {
            String key = subjectChapterBean.getSubjectId();

            if (subjectChapterMap.containsKey(key)) {
                List<SubjectChapterBean> retrievedSubjectChapterBeans = subjectChapterMap.get(key);
                retrievedSubjectChapterBeans.add(subjectChapterBean);
            } else {
                List<SubjectChapterBean> newSubjectChapterBeans = new ArrayList<>();
                newSubjectChapterBeans.add(subjectChapterBean);
                subjectChapterMap.put(key, newSubjectChapterBeans);
            }
        }

        for (SubjectBean subjectBean : subjectBeans) {
            List<SubjectChapterBean> retrievedSubjectChapterBeans = subjectChapterMap.get(subjectBean.getSubjectId());

            if (!CollectionUtils.isEmpty(retrievedSubjectChapterBeans)) {
                subjectBean.setSubjectChapterBeans(retrievedSubjectChapterBeans);
            } else {
                subjectBean.getSubjectChapterBeans().add(new SubjectChapterBean("No chapters available for " + subjectBean.getName() + " subject."));
            }
        }

        subjectsBean.setStatus(1);
        return subjectsBean;
    }
}
