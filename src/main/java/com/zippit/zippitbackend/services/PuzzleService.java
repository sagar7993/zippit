package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.repositories.PuzzleRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akash.mercer on 08-Jun-17.
 */

@Service
public class PuzzleService {
    private static final String TAG = "PuzzleService : ";

    @Autowired
    private PuzzleRepository puzzleRepository;

    private static Logger debugLogger = Logger.getLogger("debugLogs");

    private static Logger errorLogger = Logger.getLogger("errorLogs");

}
