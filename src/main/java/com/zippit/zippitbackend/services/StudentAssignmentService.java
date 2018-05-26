package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.repositories.StudentAssignmentRepository;
import com.zippit.zippitbackend.beans.StudentAssignmentBean;
import com.zippit.zippitbackend.beans.StudentAssignmentSummaryBean;
import com.zippit.zippitbackend.entities.Assignment;
import com.zippit.zippitbackend.entities.StudentAssignment;
import com.zippit.zippitbackend.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by akash.mercer on 05-Jul-17.
 */

@Service
public class StudentAssignmentService {
    private static final String TAG = "StudentAssignmentService : ";

    @Autowired
    private StudentAssignmentRepository studentAssignmentRepository;

    private static Logger debugLogger = LoggerFactory.getLogger("debugLogs");

    private static Logger errorLogger = LoggerFactory.getLogger("errorLogs");

    public void save(StudentAssignment studentAssignment) {
        studentAssignmentRepository.save(studentAssignment);
    }

    public void saveAll(List<StudentAssignment> studentAssignments) throws Exception {
        studentAssignmentRepository.save(studentAssignments);
    }

    public List<StudentAssignment> fetchAll() {
        return (List<StudentAssignment>) studentAssignmentRepository.findAll();
    }

    public StudentAssignment findById(String id) {
        return studentAssignmentRepository.findOne(id);
    }

    public List<StudentAssignmentBean> findByStudent(User student) {
        return studentAssignmentRepository.findByStudent(student);
    }

    public StudentAssignmentSummaryBean findByStudentAndAssignment(String studentId, String assignmentId) {
        StudentAssignmentSummaryBean studentAssignmentSummaryBean = new StudentAssignmentSummaryBean();

        studentAssignmentSummaryBean.setStudentAssignmentBean(studentAssignmentRepository.findByStudentAndAssignment(studentId, assignmentId));
        studentAssignmentSummaryBean.setStatus(1);

        return studentAssignmentSummaryBean;
    }

    public StudentAssignmentBean findByStudentAndAssignment(User student, Assignment assignment) {
        return studentAssignmentRepository.findByStudentAndAssignment(student, assignment);
    }
}
