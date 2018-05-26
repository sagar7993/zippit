package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.beans.SubjectChapterBean;
import com.zippit.zippitbackend.entities.Board;
import com.zippit.zippitbackend.entities.Standard;
import com.zippit.zippitbackend.entities.SubjectChapter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by akash.mercer on 09-Jul-17.
 */

@Repository
public interface SubjectChapterRepository extends CrudRepository<SubjectChapter, String> {

    @Query(value = "select new com.zippit.zippitbackend.beans.SubjectChapterBean(s.id, s.subjectChapterName, s.subjectChapterNumber, s.subject.id, s.board.id) from SubjectChapter s where s.subject.id in :subjectIds and s.board = :board and s.standard = :standard order by s.subjectChapterNumber asc")
    List<SubjectChapterBean> findBySubjectIdsAndBoardAndStandard(@Param("subjectIds") List<String> subjectIds, @Param("board") Board board, @Param("standard") Standard standard);

}
