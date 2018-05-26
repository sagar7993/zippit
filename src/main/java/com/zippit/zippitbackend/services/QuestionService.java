package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.beans.GetQuestionBankBean;
import com.zippit.zippitbackend.beans.QuestionBean;
import com.zippit.zippitbackend.beans.StatusBean;
import com.zippit.zippitbackend.entities.*;
import com.zippit.zippitbackend.repositories.QuestionRepository;
import com.zippit.zippitbackend.utils.Commons;
import com.zippit.zippitbackend.utils.Constants;
import com.zippit.zippitbackend.utils.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by akash.mercer on 07-Jul-17.
 */

@Service
public class QuestionService {
    private static final String TAG = "QuestionService : ";

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectChapterService subjectChapterService;

    @Autowired
    private QuestionTypeService questionTypeService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private StandardDivisionService standardDivisionService;

    @Autowired
    private SolutionOptionService solutionOptionService;

    private static Logger debugLogger = LoggerFactory.getLogger("debugLogs");

    private static Logger errorLogger = LoggerFactory.getLogger("errorLogs");

    public void saveAll(List<Question> questions) {
        questionRepository.save(questions);
    }

    public List<Question> findByIds(List<String> questionIds) {
        return questionRepository.findByIds(questionIds);
    }

    public List<QuestionBean> getQuestions(GetQuestionBankBean getQuestionBankBean) {
        String subjectChapterId = getQuestionBankBean.getSubjectChapterId();
        String standardDivisionId = getQuestionBankBean.getStandardDivisionId();
        String boardId = getQuestionBankBean.getBoardId();
        String standardId = standardDivisionService.findById(standardDivisionId).getStandard().getId();
        List<QuestionBean> questionBeans = questionRepository.findByBoardAndStandardAndSubjectChapter(boardId, standardId, subjectChapterId);
        for(QuestionBean questionBean : questionBeans) {
            questionBean.setSolutionOptionBeans(solutionOptionService.findByQuestionId(questionBean.getQuestionId()));
        }
        return questionBeans;
    }

    @Transactional(rollbackFor = {Exception.class})
    public StatusBean readQuestionSheet(String fileName) {
        StatusBean statusBean = new StatusBean();
        String[] fileNameParts = fileName.split("_");
        Subject retrievedSubject = subjectService.findByType(Integer.parseInt(fileNameParts[0]));

        if (retrievedSubject == null) {
            errorLogger.error(TAG + "Error in finding Subject with id : " + fileNameParts[0] + " in file : " + fileName);
            statusBean.setStatus(0);
            statusBean.setMessage(TAG + "Error in finding Subject with id : " + fileNameParts[0] + " in file : " + fileName);
            return statusBean;
        }

        SubjectChapter retrievedSubjectChapter = subjectChapterService.findById(fileNameParts[1]);

        if (retrievedSubjectChapter == null) {
            errorLogger.error(TAG + "Error in finding SubjectChapter with id : " + fileNameParts[1] + " in file : " + fileName);
            statusBean.setStatus(0);
            statusBean.setMessage(TAG + "Error in finding SubjectChapter with id : " + fileNameParts[1] + " in file : " + fileName);
            return statusBean;
        }

        Map<Integer, QuestionType> questionTypeMap = getQuestionTypeMap();

        String OSName = System.getProperty("os.name");
        String path = OSName.startsWith("Windows") ? Constants.WINDOWS_QUESTION_PAPER_FILE_PATH : Constants.UBUNTU_QUESTION_PAPER_FILE_PATH;
        File file = new File(path + File.separator + fileName);

        XSSFWorkbook xssfWorkbook;

        try {
            xssfWorkbook = FileUtils.getWorkbookFromFile(file);
        } catch (Exception e) {
            errorLogger.error(TAG + "Error in opening Workbook file : " + file.getAbsolutePath());
            statusBean.setStatus(0);
            statusBean.setMessage(TAG + "Error in opening Workbook file : " + file.getAbsolutePath());
            return statusBean;
        }

        XSSFSheet xssfSheet = FileUtils.getSheetFromWorkBook(xssfWorkbook);

        if (xssfSheet == null) {
            errorLogger.error(TAG + "No Sheet found in Workbook file : " + file.getAbsolutePath());
            statusBean.setStatus(0);
            statusBean.setMessage(TAG + "No Sheet found in Workbook file : " + file.getAbsolutePath());
            return statusBean;
        }

        int lastRowIndex = xssfSheet.getLastRowNum();
        List<Question> questions = new ArrayList<>();
        List<SolutionOption> solutionOptions = new ArrayList<>();
        int i = 1;

        try {
            for (i = 1; i <= lastRowIndex; i++) {
                XSSFRow xssfRow = xssfSheet.getRow(i);
                Question question = new Question();
                if (xssfRow.getCell(2) == null) {
                    break;
                }
                question.setQuestionText(xssfRow.getCell(2).getStringCellValue());
                question.setMarks(1);
                question.setSubjectChapter(retrievedSubjectChapter);
                xssfRow.getCell(10).setCellType(CellType.BOOLEAN);
                question.setFormula(xssfRow.getCell(10).getBooleanCellValue());
                xssfRow.getCell(8).setCellType(CellType.STRING);
                question.setQuestionType(questionTypeMap.get(Integer.parseInt(xssfRow.getCell(8).getStringCellValue())));
                //Build SolutionOptions for given Question
                solutionOptions.addAll(getSolutionOptionsForQuestion(xssfRow, question));
                questions.add(question);
            }
        } catch (Exception e) {
            errorLogger.error(TAG + "Error in looping through rows of excel sheet at row number = " + i);
            statusBean.setStatus(0);
            statusBean.setMessage(TAG + "Error in looping through rows of excel sheet at row number = " + i);
            return statusBean;
        }

        try {
            questionService.saveAll(questions);
            try {
                solutionOptionService.saveAll(solutionOptions);
            } catch (Exception e) {
                errorLogger.error(TAG + "Error in saving SolutionOptions with error : " + e.getMessage());
                statusBean.setStatus(0);
                statusBean.setMessage(TAG + "Error in saving SolutionOptions with error : " + e.getMessage());
                throw e;
            }
        } catch (Exception e) {
            errorLogger.error(TAG + "Error in saving Questions with error : " + e.getMessage());
            statusBean.setStatus(0);
            statusBean.setMessage(TAG + "Error in saving Questions with error : " + e.getMessage());
            throw e;
        }

        try {
            if (xssfWorkbook != null) {
                xssfWorkbook.close();
            }
        } catch (IOException e) {
            errorLogger.error(TAG + "Error in closing workbook file : " + file.getAbsolutePath());
        }

        statusBean.setStatus(1);
        return statusBean;
    }

    private Map<Integer, QuestionType> getQuestionTypeMap() {
        List<QuestionType> questionTypes = questionTypeService.fetchAll();

        Map<Integer, QuestionType> questionTypeMap = new HashMap<>();

        for (QuestionType questionType : questionTypes) {
            questionTypeMap.put(questionType.getType(), questionType);
        }

        return questionTypeMap;
    }

    private List<SolutionOption> getSolutionOptionsForQuestion(XSSFRow xssfRow, Question question) {
        List<SolutionOption> solutionOptions = new ArrayList<>();

        xssfRow.getCell(7).setCellType(CellType.STRING);

        xssfRow.getCell(9).setCellType(CellType.BOOLEAN);

        if (xssfRow.getCell(3) != null) {
            xssfRow.getCell(3).setCellType(CellType.STRING);
            if (!StringUtils.isEmpty(xssfRow.getCell(3).getStringCellValue())) {
                solutionOptions.add(getSolutionOptionFromCell(xssfRow.getCell(3), question, xssfRow.getCell(7).getStringCellValue(), xssfRow.getCell(9).getBooleanCellValue()));
            }
        }

        if (xssfRow.getCell(4) != null) {
            xssfRow.getCell(4).setCellType(CellType.STRING);
            if (!StringUtils.isEmpty(xssfRow.getCell(4).getStringCellValue())) {
                solutionOptions.add(getSolutionOptionFromCell(xssfRow.getCell(4), question, xssfRow.getCell(7).getStringCellValue(), xssfRow.getCell(9).getBooleanCellValue()));
            }
        }

        if (xssfRow.getCell(5) != null) {
            xssfRow.getCell(5).setCellType(CellType.STRING);
            if (!StringUtils.isEmpty(xssfRow.getCell(5).getStringCellValue())) {
                solutionOptions.add(getSolutionOptionFromCell(xssfRow.getCell(5), question, xssfRow.getCell(7).getStringCellValue(), xssfRow.getCell(9).getBooleanCellValue()));
            }
        }

        if (xssfRow.getCell(6) != null) {
            xssfRow.getCell(6).setCellType(CellType.STRING);
            if (!StringUtils.isEmpty(xssfRow.getCell(6).getStringCellValue())) {
                solutionOptions.add(getSolutionOptionFromCell(xssfRow.getCell(6), question, xssfRow.getCell(7).getStringCellValue(), xssfRow.getCell(9).getBooleanCellValue()));
            }
        }

        return solutionOptions;
    }

    private SolutionOption getSolutionOptionFromCell(XSSFCell xssfCell, Question question, String solution, Boolean formula) {
        SolutionOption solutionOption = new SolutionOption();

        String solutionOptionText = null;

        try {
            solutionOptionText = Commons.amountDecimalFormat.format(Double.parseDouble(xssfCell.getStringCellValue()));
        } catch (NumberFormatException e) {
            //Do Nothing
        }

        solutionOption.setSolutionOptionText(solutionOptionText != null ? solutionOptionText : xssfCell.getStringCellValue());
        solutionOption.setSolution(solutionOption.getSolutionOptionText().equals(solution));
        solutionOption.setFormula(formula);
        solutionOption.setQuestion(question);
        return solutionOption;
    }
}
