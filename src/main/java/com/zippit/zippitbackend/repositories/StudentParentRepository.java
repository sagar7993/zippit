package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.entities.StudentParent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by akash.mercer on 05-Jun-17.
 */

@Repository
public interface StudentParentRepository extends CrudRepository<StudentParent, String> {

}
