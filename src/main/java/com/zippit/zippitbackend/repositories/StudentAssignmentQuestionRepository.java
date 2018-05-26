package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.beans.StudentAssignmentQuestionBean;
import com.zippit.zippitbackend.entities.Assignment;
import com.zippit.zippitbackend.entities.StudentAssignmentQuestion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by akash.mercer on 06-Aug-17.
 */

@Repository
public interface StudentAssignmentQuestionRepository extends CrudRepository<StudentAssignmentQuestion, String> {

    @Query(value = "select new com.zippit.zippitbackend.beans.StudentAssignmentQuestionBean(s.assignmentQuestion.question.id, s.assignmentQuestion.question.questionText, s.assignmentQuestion.question.formula, s.attempted, s.correct) from StudentAssignmentQuestion s where s.assignmentQuestion.assignment = :assignment and s.student.admin = false")
    List<StudentAssignmentQuestionBean> findByAssignment(@Param("assignment") Assignment assignment);

    @Query(value = "select s from StudentAssignmentQuestion s where s.assignmentQuestion.assignment.id = :assignmentId")
    List<StudentAssignmentQuestion> findByAssignmentId(@Param("assignmentId") String assignmentId);

}
