package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.entities.Board;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by akash.mercer on 07-Jul-17.
 */

@Repository
public interface BoardRepository extends CrudRepository<Board, String> {

    @Query(value = "select count(*) from Board")
    Integer getCount();

    Board findByType(Integer type);

}
