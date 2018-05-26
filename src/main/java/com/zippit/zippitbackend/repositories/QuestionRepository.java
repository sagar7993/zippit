package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.beans.QuestionBean;
import com.zippit.zippitbackend.entities.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by akash.mercer on 07-Jul-17.
 */

@Repository
public interface QuestionRepository extends CrudRepository<Question, String> {

    @Query(value = "select q from Question q where q.id in :questionIds")
    List<Question> findByIds(@Param("questionIds") List<String> questionIds);

    @Query(value = "select new com.zippit.zippitbackend.beans.QuestionBean(q.id, q.questionText, q.questionImageUrl, q.marks, q.formula, q.questionType.type) from Question q where q.subjectChapter.board.id = :boardId and q.subjectChapter.standard.id = :standardId and q.subjectChapter.id = :subjectChapterId")
    List<QuestionBean> findByBoardAndStandardAndSubjectChapter(@Param("boardId") String boardId, @Param("standardId") String standardId, @Param("subjectChapterId") String subjectChapterId);

}
