package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.beans.SubjectBean;
import com.zippit.zippitbackend.entities.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by akash.mercer on 05-Jul-17.
 */

@Repository
public interface SubjectRepository extends CrudRepository<Subject, String> {

    @Query(value = "select count(*) from Subject")
    Integer getCount();

    Subject findByType(Integer type);

    @Query(value = "select new com.zippit.zippitbackend.beans.SubjectBean(s.id, s.name, s.type) from Subject s where s.active = true")
    List<SubjectBean> findAllActiveSubjectBean();

    @Query(value = "select new com.zippit.zippitbackend.beans.SubjectBean(s.id, s.name, s.type, s.active) from Subject s")
    List<SubjectBean> findAllSubjectBean();

}
