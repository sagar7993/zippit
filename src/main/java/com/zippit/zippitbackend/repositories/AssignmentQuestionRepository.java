package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.beans.QuestionBean;
import com.zippit.zippitbackend.entities.Assignment;
import com.zippit.zippitbackend.entities.AssignmentQuestion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by akash.mercer on 05-Jul-17.
 */

@Repository
public interface AssignmentQuestionRepository extends CrudRepository<AssignmentQuestion, String> {

    @Query(value = "select new com.zippit.zippitbackend.beans.QuestionBean(a.question.id, a.question.questionText, a.question.questionImageUrl, a.question.marks, a.question.formula, a.question.questionType.type) from AssignmentQuestion a where a.assignment.id = :assignmentId")
    List<QuestionBean> findByAssignment(@Param("assignmentId") String assignmentId);

    List<AssignmentQuestion> findByAssignment(Assignment assignment);

}
