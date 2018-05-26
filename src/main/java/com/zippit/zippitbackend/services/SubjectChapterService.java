package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.beans.SubjectChapterBean;
import com.zippit.zippitbackend.entities.SubjectChapter;
import com.zippit.zippitbackend.repositories.SubjectChapterRepository;
import com.zippit.zippitbackend.entities.Board;
import com.zippit.zippitbackend.entities.Standard;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by akash.mercer on 09-Jul-17.
 */

@Service
public class SubjectChapterService {
    private static final String TAG = "SubjectChapterService : ";

    @Autowired
    private SubjectChapterRepository subjectChapterRepository;

    private static Logger debugLogger = Logger.getLogger("debugLogs");

    private static Logger errorLogger = Logger.getLogger("errorLogs");

    public SubjectChapter findById(String id) {
        return subjectChapterRepository.findOne(id);
    }

    public List<SubjectChapterBean> findBySubjectIdsAndBoardAndStandard(List<String> subjectIds, Board board, Standard standard) {
        return subjectChapterRepository.findBySubjectIdsAndBoardAndStandard(subjectIds, board, standard);
    }
}
