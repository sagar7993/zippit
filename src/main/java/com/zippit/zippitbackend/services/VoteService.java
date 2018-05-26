package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.entities.Vote;
import com.zippit.zippitbackend.repositories.VoteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akash.mercer on 05-Jun-17.
 */

@Service
public class VoteService {
    private static final String TAG = "VoteService : ";

    @Autowired
    private VoteRepository voteRepository;

    private static Logger debugLogger = Logger.getLogger("debugLogs");

    private static Logger errorLogger = Logger.getLogger("errorLogs");

    public void save(Vote vote) throws Exception {
        voteRepository.save(vote);
    }
}
