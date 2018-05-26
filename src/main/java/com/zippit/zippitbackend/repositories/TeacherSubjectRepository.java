package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.beans.StandardBean;
import com.zippit.zippitbackend.beans.SubjectBean;
import com.zippit.zippitbackend.entities.Standard;
import com.zippit.zippitbackend.entities.TeacherSubject;
import com.zippit.zippitbackend.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by akash.mercer on 18-Jun-17.
 */

@Repository
public interface TeacherSubjectRepository extends CrudRepository<TeacherSubject, String> {

    @Transactional
    @Query(value = "SELECT new com.zippit.zippitbackend.beans.SubjectBean(t.subject.id, t.subject.name, t.subject.type) FROM TeacherSubject t WHERE t.teacher = :teacher group by t.subject")
    List<SubjectBean> findByTeacher(@Param("teacher") User teacher);

    @Query(value = "SELECT new com.zippit.zippitbackend.beans.SubjectBean(t.subject.id, t.subject.name, t.subject.type) FROM TeacherSubject t WHERE t.teacher = :teacher and t.standardDivision.standard = :standard group by t.subject")
    List<SubjectBean> findByTeacherAndStandard(@Param("teacher") User teacher, @Param("standard") Standard standard);

    @Query(value = "select new com.zippit.zippitbackend.beans.StandardBean(t.standardDivision.standard.id, t.standardDivision.standard.standardName) from TeacherSubject t where t.teacher = :teacher group by t.standardDivision.standard order by t.standardDivision.standard.standardName asc")
    List<StandardBean> getStandards(@Param("teacher") User teacher);
}
