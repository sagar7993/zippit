package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.entities.Badge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by akash.mercer on 08-Jun-17.
 */

@Repository
public interface BadgeRepository extends CrudRepository<Badge, String> {

    @Query(value = "select count(*) from Badge")
    Integer getCount();

    Badge findByType(Integer type);

}
