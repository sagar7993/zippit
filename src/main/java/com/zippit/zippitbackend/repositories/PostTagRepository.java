package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.entities.PostTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by akash.mercer on 05-Jun-17.
 */

@Repository
public interface PostTagRepository extends CrudRepository<PostTag, String> {

}
