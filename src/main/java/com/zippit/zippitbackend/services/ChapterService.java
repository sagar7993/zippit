package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.beans.AddChapterBean;
import com.zippit.zippitbackend.beans.SubjectChapterBean;
import com.zippit.zippitbackend.entities.SubjectChapter;
import com.zippit.zippitbackend.repositories.SubjectChapterRepository;
import com.zippit.zippitbackend.beans.StatusBean;
import com.zippit.zippitbackend.entities.Board;
import com.zippit.zippitbackend.entities.Standard;
import com.zippit.zippitbackend.entities.Subject;
import com.zippit.zippitbackend.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sagar Jain on 7/23/2017.
 */

@Service
public class ChapterService {
    private static final String TAG = "ChapterService : ";

    @Autowired
    private SubjectChapterRepository subjectChapterRepository;

    @Autowired
    private StandardService standardService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private SubjectService subjectService;

    private static Logger debugLogger = LoggerFactory.getLogger("debugLogs");

    private static Logger errorLogger = LoggerFactory.getLogger("errorLogs");

    public List<SubjectChapterBean> findBySubjectAndBoardAndStandard(String subjectId, String boardId, String standardId) {

        List<String> subjectIdList = new ArrayList<>();
        subjectIdList.add(subjectId);

        Board retrievedBoard = boardService.findById(boardId);
        Standard retrievedStandard = standardService.findById(standardId);

        return subjectChapterRepository.findBySubjectIdsAndBoardAndStandard(subjectIdList, retrievedBoard, retrievedStandard);

    }

    public StatusBean save(AddChapterBean addChapterBean) {

        StatusBean statusBean = new StatusBean(0, Constants.SOMETHING_WENT_WRONG);

        Board retrievedBoard = boardService.findById(addChapterBean.getBoardId());
        Standard retrievedStandard = standardService.findById(addChapterBean.getStandardId());
        Subject retrievedSubject = subjectService.findById(addChapterBean.getSubjectId());

        if (retrievedBoard == null) {
            errorLogger.error(TAG + "Error in finding board with id : " + addChapterBean.getBoardId());
            return statusBean;
        }
        if (retrievedStandard == null) {
            errorLogger.error(TAG + "Error in finding standard with id : " + addChapterBean.getStandardId());
            return statusBean;
        }
        if (retrievedStandard == null) {
            errorLogger.error(TAG + "Error in finding subject with id : " + addChapterBean.getSubjectId());
            return statusBean;
        }

        SubjectChapter subjectChapter = new SubjectChapter();
        subjectChapter.setBoard(retrievedBoard);
        subjectChapter.setStandard(retrievedStandard);
        subjectChapter.setSubject(retrievedSubject);
        subjectChapter.setSubjectChapterName(addChapterBean.getChapterName());
        subjectChapter.setSubjectChapterNumber(addChapterBean.getChapterNumber());

        subjectChapterRepository.save(subjectChapter);

        statusBean.setStatus(1);

        return statusBean;

    }

}
