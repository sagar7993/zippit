package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.entities.User;
import com.zippit.zippitbackend.entities.StandardDivision;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by akash.mercer on 07-Jul-17.
 */

@Repository
public interface StandardDivisionRepository extends CrudRepository<StandardDivision, String> {

    @Query(value = "select s from StandardDivision s where s.id in :standardDivisionIds")
    List<StandardDivision> findByIds(@Param("standardDivisionIds") List<String> standardDivisionIds);

    List<StandardDivision> findByInstitute(User institute);

}
