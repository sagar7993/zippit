package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.entities.Announcement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by akash.mercer on 04-Jun-17.
 */

@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement, String> {

}
