package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.beans.StudentAssignmentBean;
import com.zippit.zippitbackend.entities.Assignment;
import com.zippit.zippitbackend.entities.StudentAssignment;
import com.zippit.zippitbackend.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by akash.mercer on 05-Jul-17.
 */

@Repository
public interface StudentAssignmentRepository extends CrudRepository<StudentAssignment, String> {

    @Query(value = "select new com.zippit.zippitbackend.beans.StudentAssignmentBean(s.id, s.assignment.id, s.assignment.assignmentTitle, s.studentSolution, s.marks, s.attempted, s.correct) from StudentAssignment s where s.student = :student")
    List<StudentAssignmentBean> findByStudent(@Param("student") User student);

    @Query(value = "select new com.zippit.zippitbackend.beans.StudentAssignmentBean(s.id, s.assignment.id, s.assignment.assignmentTitle, s.studentSolution, s.marks, s.attempted, s.correct) from StudentAssignment s where s.student.id = :studentId and s.assignment.id = :assignmentId")
    StudentAssignmentBean findByStudentAndAssignment(@Param("studentId") String studentId, @Param("assignmentId") String assignmentId);

    @Query(value = "select new com.zippit.zippitbackend.beans.StudentAssignmentBean(s.id, s.assignment.id, s.assignment.assignmentTitle, s.studentSolution, s.marks, s.attempted, s.correct) from StudentAssignment s where s.student = :student and s.assignment = :assignment")
    StudentAssignmentBean findByStudentAndAssignment(@Param("student") User student, @Param("assignment") Assignment assignment);

}
