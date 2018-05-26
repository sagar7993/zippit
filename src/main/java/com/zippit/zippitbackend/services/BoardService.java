package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.entities.Board;
import com.zippit.zippitbackend.repositories.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akash.mercer on 07-Jul-17.
 */

@Service
public class BoardService {
    private static final String TAG = "BoardService : ";

    @Autowired
    private BoardRepository boardRepository;

    private static Logger debugLogger = LoggerFactory.getLogger("debugLogs");

    private static Logger errorLogger = LoggerFactory.getLogger("errorLogs");

    public Integer getCount() {
        return boardRepository.getCount();
    }

    public Board findByType(Integer type) {
        return boardRepository.findByType(type);
    }

    public Board findById(String boardId) {
        return boardRepository.findOne(boardId);
    }
}
