package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.beans.SubjectChapterBean;
import com.zippit.zippitbackend.beans.AssignmentBean;
import com.zippit.zippitbackend.entities.Assignment;
import com.zippit.zippitbackend.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by akash.mercer on 04-Jun-17.
 */

@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, String> {

    @Query(value = "select new com.zippit.zippitbackend.beans.AssignmentBean(a.id, a.assignmentTitle, a.deadlineDate, a.marks, a.subjectChapter.subject.name, a.subjectChapter.subjectChapterName, a.teacher.id) from Assignment a where a.id = :id")
    AssignmentBean findById(@Param("id") String id);

    @Query(value = "select new com.zippit.zippitbackend.beans.AssignmentBean(a.id, a.assignmentTitle, a.deadlineDate, a.marks, a.subjectChapter.subject.name, a.subjectChapter.subjectChapterName, a.teacher.id) from Assignment a where a.teacher = :teacher")
    List<AssignmentBean> findByTeacher(@Param("teacher") User teacher);

    @Query(value = "select new com.zippit.zippitbackend.beans.SubjectChapterBean(a.subjectChapter.id, a.subjectChapter.subjectChapterName, a.subjectChapter.subjectChapterNumber, a.subjectChapter.subject.id, a.subjectChapter.board.id) from Assignment a where a.teacher = :teacher group by a.subjectChapter")
    List<SubjectChapterBean> findSubjectChaptersFromAssignmentsByTeacher(@Param("teacher") User teacher);
}
