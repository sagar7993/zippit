package com.zippit.zippitbackend.repositories;

import com.zippit.zippitbackend.entities.Puzzle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by akash.mercer on 08-Jun-17.
 */

@Repository
public interface PuzzleRepository extends CrudRepository<Puzzle, String> {

}
