package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.entities.TagType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by akash.mercer on 05-Jun-17.
 */

@Repository
public interface TagTypeRepository extends CrudRepository<TagType, String> {

    @Query(value = "select count(*) from TagType")
    Integer getCount();

    TagType findByType(Integer type);

}
