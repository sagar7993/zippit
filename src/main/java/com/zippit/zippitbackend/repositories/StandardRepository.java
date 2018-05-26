package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.entities.Standard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by akash.mercer on 04-Jun-17.
 */

@Repository
public interface StandardRepository extends CrudRepository<Standard, String> {

    @Query(value = "select count(*) from Standard")
    Integer getCount();

    Standard findByStandardName(Integer standardName);
}
