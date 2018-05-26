package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.entities.AssignmentTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by akash.mercer on 05-Jun-17.
 */

@Repository
public interface AssignmentTagRepository extends CrudRepository<AssignmentTag, String> {

}
