package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.entities.TeacherInstitute;
import com.zippit.zippitbackend.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by akash.mercer on 04-Jun-17.
 */

@Repository
public interface TeacherInstituteRepository extends CrudRepository<TeacherInstitute, String> {

    TeacherInstitute findByTeacher(User teacher);

}
