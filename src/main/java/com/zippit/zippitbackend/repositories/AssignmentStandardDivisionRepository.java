package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.beans.SubjectChapterBean;
import com.zippit.zippitbackend.beans.AssignmentBean;
import com.zippit.zippitbackend.beans.StandardBean;
import com.zippit.zippitbackend.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by akash.mercer on 05-Jul-17.
 */

@Repository
public interface AssignmentStandardDivisionRepository extends PagingAndSortingRepository<AssignmentStandardDivision, String> {

    @Query(value = "select new com.zippit.zippitbackend.beans.AssignmentBean(a.assignment.id, a.assignment.assignmentTitle, a.assignment.deadlineDate, a.assignment.marks, a.assignment.subjectChapter.subject.name, a.assignment.teacher.id) from AssignmentStandardDivision a where a.standardDivision = :standardDivision and a.assignment.subjectChapter = :subjectChapter")
    Page<AssignmentBean> findByStandardDivisionAndSubjectChapter(@Param("standardDivision") StandardDivision standardDivision, @Param("subjectChapter") SubjectChapter subjectChapter, Pageable pageable);

    @Deprecated
    @Query(value = "select new com.zippit.zippitbackend.beans.StandardBean(a.standardDivision.standard.id, a.standardDivision.standard.standardName) from AssignmentStandardDivision a where a.assignment.teacher = :teacher group by a.standardDivision.standard order by a.standardDivision.standard.standardName asc")
    List<StandardBean> getStandardsFromAssignmentsByTeacher(@Param("teacher") User teacher);

    @Query(value = "select a from AssignmentStandardDivision a where a.assignment.id = :assignmentId")
    List<AssignmentStandardDivision> findByAssignmentId(@Param("assignmentId") String assignmentId);

    @Query(value = "select new com.zippit.zippitbackend.beans.SubjectChapterBean(a.assignment.subjectChapter.id, a.assignment.subjectChapter.subjectChapterName, a.assignment.subjectChapter.subjectChapterNumber, a.assignment.subjectChapter.subject.id, a.assignment.subjectChapter.board.id) from AssignmentStandardDivision a where a.standardDivision.standard = :standard and a.standardDivision.institute = :institute group by a.assignment.subjectChapter")
    List<SubjectChapterBean> getSubjectsForStandard(@Param("institute") User institute, @Param("standard") Standard standard);

    @Query(value = "select new com.zippit.zippitbackend.beans.AssignmentBean(a.assignment.id, a.assignment.assignmentTitle, a.assignment.deadlineDate, a.assignment.marks, a.assignment.subjectChapter.subject.name, a.assignment.teacher.id) from AssignmentStandardDivision a where a.standardDivision.institute = :institute and a.assignment.subjectChapter = :subjectChapter group by a.assignment")
    Page<AssignmentBean> findByInstituteAndSubjectChapter(@Param("institute") User institute, @Param("subjectChapter") SubjectChapter subjectChapter, Pageable pageable);
}
