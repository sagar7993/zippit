package com.zippit.zippitbackend.controllers;

import com.zippit.zippitbackend.beans.AddChapterBean;
import com.zippit.zippitbackend.beans.SubjectChapterBean;
import com.zippit.zippitbackend.services.ChapterService;
import com.zippit.zippitbackend.beans.StatusBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Sagar Jain on 7/23/2017.
 */

@RestController
@RequestMapping(value = "/subjectChapter")
public class SubjectChapterController {

    @Autowired
    ChapterService chapterService;

    @RequestMapping(value = "/findBySubjectAndBoardAndStandard/{subjectId}/{boardId}/{standardId}", method = RequestMethod.GET)
    public @ResponseBody List<SubjectChapterBean> findBySubjectAndBoardAndStandard(@PathVariable("subjectId") String subjectId, @PathVariable("boardId") String boardId, @PathVariable("standardId") String standardId){
        return chapterService.findBySubjectAndBoardAndStandard(subjectId, boardId, standardId);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody StatusBean save(@RequestBody AddChapterBean addChapterBean){
        return chapterService.save(addChapterBean);
    }

}
