package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.entities.AppConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by akash.mercer on 23-Jul-17.
 */

@Repository
public interface AppConfigRepository extends CrudRepository<AppConfig, String> {

    @Query(value = "select count(*) from AppConfig")
    Integer getCount();

}
