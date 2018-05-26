package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.beans.*;
import com.zippit.zippitbackend.entities.StudentAssignmentQuestion;
import com.zippit.zippitbackend.entities.User;
import com.zippit.zippitbackend.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by akash.mercer on 24-Jul-17.
 */

@Service
public class FileService {
    private static final String TAG = "fileUploadService : ";

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private StudentAssignmentQuestionService studentAssignmentQuestionService;

    @Autowired
    private MailService mailService;

    private static Logger debugLogger = LoggerFactory.getLogger("debugLogs");

    private static Logger errorLogger = LoggerFactory.getLogger("errorLogs");

    public void executeShellScript(String arguement, String scriptType) throws Exception {
        TimeUnit.SECONDS.sleep(3);
        String OSName = System.getProperty("os.name"), command, scriptName;
        if(scriptType.equalsIgnoreCase("DOWNLOAD_ASSIGNMENT")) {
            scriptName = "htmlToPdfAssignmentDownload";
        } else if(scriptType.equalsIgnoreCase("DOWNLOAD_ASSIGNMENT_ANALYSIS")) {
            scriptName = "htmlToPdfAssignmentAnalysisDownload";
        } else {
            scriptName = "htmlToPdfAssignmentAnalysisDownload";
        }
        if(OSName.startsWith("Windows")) {
            String scriptFilePath = Constants.WINDOWS_ASSIGNMENT_FILE_PATH;
            File scriptFileDirectory = new File(scriptFilePath);
            scriptFilePath = scriptFileDirectory.getAbsolutePath() + File.separator + scriptName + ".bat";
            command = "cmd /c " + scriptFilePath;
            Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "start " + command, arguement});
        } else {
            String scriptFilePath = Constants.UBUNTU_ASSIGNMENT_FILE_PATH, line;
            File scriptFileDirectory = new File(scriptFilePath);
            scriptFilePath = scriptFileDirectory.getAbsolutePath() + File.separator + scriptName + ".sh";
            command = scriptFilePath;
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", command, arguement);
            Process process = processBuilder.start();
            process.waitFor();
            process.destroy();
//            Process process = Runtime.getRuntime().exec("./wkhtmltopdf /home/ubuntu/assignment/" + arguement + ".html " + arguement + ".pdf",
//                    null, new File("/home/ubuntu/wkhtmltox/bin"));
//            process.waitFor();
//            process = Runtime.getRuntime().exec("mv " + arguement + ".pdf /var/www/html/pdf/" + arguement + ".pdf");
//            process.waitFor();
//            process.destroy();
        }
    }

    public HtmlToPdfBean getAssignmentHtml(String assignmentId) throws IOException {
        HtmlToPdfBean htmlToPdfBean = new HtmlToPdfBean(0, Constants.SOMETHING_WENT_WRONG);
        AssignmentBean retrievedAssignment = assignmentService.findByAssignment(assignmentId);
        if (retrievedAssignment == null) {
            errorLogger.error(TAG + "Error in finding Assignment with id : " + assignmentId);
            return htmlToPdfBean;
        }
        htmlToPdfBean.setAssignmentId(retrievedAssignment.getAssignmentId());
        return saveAssignmentHtmlFileFromHtmlString(htmlToPdfBean.getAssignmentId(), retrievedAssignment);
    }

    public HtmlToPdfBean getAssignmentAnalysisHtml(String assignmentId) throws IOException {
        HtmlToPdfBean htmlToPdfBean = new HtmlToPdfBean(0, Constants.SOMETHING_WENT_WRONG);
        AssignmentBean retrievedAssignment = assignmentService.findByAssignment(assignmentId);
        if (retrievedAssignment == null) {
            errorLogger.error(TAG + "Error in finding Assignment with id : " + assignmentId);
            return htmlToPdfBean;
        }
        htmlToPdfBean.setAssignmentId(retrievedAssignment.getAssignmentId());
        return saveAnalysisHtmlFileFromHtmlString(htmlToPdfBean.getAssignmentId() + "_analysis", retrievedAssignment);
    }

    public StatusBean sendAssignmentAnalysisPdfEmail(String assignmentId, String teacherId) throws IOException {
        StatusBean statusBean = new StatusBean(0, Constants.SOMETHING_WENT_WRONG);
        AssignmentBean retrievedAssignment = assignmentService.findByAssignment(assignmentId);
        if (retrievedAssignment == null) {
            errorLogger.error(TAG + "Error in finding Assignment with id : " + assignmentId);
            return statusBean;
        }
        User retrievedTeacher = userService.findById(teacherId);
        if (retrievedTeacher == null) {
            errorLogger.error(TAG + "Error in finding Teacher with id : " + teacherId);
            return statusBean;
        }
        HtmlToPdfBean htmlToPdfBean = saveAnalysisHtmlFileFromHtmlString(assignmentId + "_analysis", retrievedAssignment);
        if (htmlToPdfBean.getStatus() == 1) {
            statusBean.setStatus(1);
        } else {
            statusBean.setStatus(0);
            statusBean.setMessage(htmlToPdfBean.getMessage());
            return statusBean;
        }
        try {
            mailService.sendFile(retrievedTeacher.getEmail(), htmlToPdfBean.getPdfFilePath());
            statusBean.setStatus(1);
        } catch (Exception e) {
            errorLogger.error(TAG + "Error in mailing PDF file to email : " + retrievedTeacher.getEmail());
            statusBean.setStatus(0);
            statusBean.setMessage(Constants.SOMETHING_WENT_WRONG);
            return statusBean;
        }
        return statusBean;
    }

    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    private String getFormattedDateString(Long time) {
        Date deadlineDate = new Date(time);
        SimpleDateFormat df2 = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String deadline = df2.format(deadlineDate);
        return deadline;
    }

    private boolean isNumeric(String text) {
        return text != null && text.matches("[-+]?\\d*\\.?\\d+");
    }

    private String getArithmeticallyFormattedText(String text) {
//        if(isNumeric(text)) {
////            double number = Double.parseDouble(text);
////            number = Math.floor(number * 100) / 100;
////            if(number == (int) number) {
////                number = (int) number;
////            }
////            text = String.valueOf(number);
//        } else {
//            if(text.contains("sqrt")) {
//                int firstIndexOfBrace = text.indexOf("{");
//                int lastIndexOfBrace = text.lastIndexOf("}");
//                text = "√ " + text.substring((firstIndexOfBrace + 1), lastIndexOfBrace);
//            }
//        }
        return text;
    }

    public HtmlToPdfBean saveAssignmentHtmlFileFromHtmlString(String fileNameWithoutExtension, AssignmentBean assignmentBean) throws IOException {

        HtmlToPdfBean htmlToPdfBean = new HtmlToPdfBean(0, Constants.SOMETHING_WENT_WRONG);
        String OSName = System.getProperty("os.name");

        String templateRootPath = OSName.startsWith("Windows") ? Constants.WINDOWS_ASSIGNMENT_TEMPLATE_PATH : Constants.UBUNTU_ASSIGNMENT_TEMPLATE_PATH;
        String generatedHTMLFileRootPath = OSName.startsWith("Windows") ? Constants.WINDOWS_ASSIGNMENT_FILE_PATH : Constants.UBUNTU_ASSIGNMENT_FILE_PATH;

        File generatedHTMLFileDirectory = new File(generatedHTMLFileRootPath);
        String generatedHTMLFilePath = generatedHTMLFileDirectory.getAbsolutePath() + File.separator + fileNameWithoutExtension + ".html";

        String styleTagHTMLContent = readFile(templateRootPath + "style.html", StandardCharsets.UTF_8);
        String optionSolutionHTMLContent = readFile(templateRootPath + "option_answer_repeater.html", StandardCharsets.UTF_8);
        String mainAssignmentHTMLContent = readFile(templateRootPath + "assignment.html", StandardCharsets.UTF_8);

        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("STYLE_TAG", styleTagHTMLContent);
        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("ASSIGNMENT_TITLE", assignmentBean.getAssignmentTitle());
        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("ASSIGNMENT_DIVISIONS", StringUtils.collectionToCommaDelimitedString(assignmentBean.getStandardDivisionNames()));
        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("ASSIGNMENT_CHAPTER", assignmentBean.getSubjectChapterName());
        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("ASSIGNMENT_SUBJECT", assignmentBean.getSubject());
        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("ASSIGNMENT_MARKS", String.valueOf(assignmentBean.getMarks()));
        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("ASSIGNMENT_DEADLINE", getFormattedDateString(assignmentBean.getDeadlineDate()));

        String allQuestionsContent = ""; int questionCounter = 0;
        String[] alphabets = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        for(QuestionBean questionBean : assignmentBean.getQuestionBeans()) {
            questionCounter++; String allOptionsContent = ""; int optionCounter = 0;
            String questionRepeaterHTMLContent = readFile(templateRootPath + "question_repeater.html", StandardCharsets.UTF_8);
            String questionTextRepeaterHTMLContent = readFile(templateRootPath + "question_text_repeater.html", StandardCharsets.UTF_8);
            questionTextRepeaterHTMLContent = questionTextRepeaterHTMLContent.replace("QUESTION_NUMBER_INDEX", String.valueOf(questionCounter));
            questionTextRepeaterHTMLContent = questionTextRepeaterHTMLContent.replace("ASSIGNMENT_QUESTION_TEXT", getArithmeticallyFormattedText(questionBean.getQuestionText()));
            questionRepeaterHTMLContent = questionRepeaterHTMLContent.replace("ASSIGNMENT_QUESTION_TEXT_REPEATER", questionTextRepeaterHTMLContent);
            for(SolutionOptionBean solutionOptionBean : questionBean.getSolutionOptionBeans()) {
                String optionRepeaterHTMLContent = readFile(templateRootPath + "option_repeater.html", StandardCharsets.UTF_8);
                optionRepeaterHTMLContent = optionRepeaterHTMLContent.replace("ASSIGNMENT_QUESTION_OPTION_INDEX", alphabets[optionCounter]);
                optionRepeaterHTMLContent = optionRepeaterHTMLContent.replace("ASSIGNMENT_QUESTION_OPTION_TEXT", getArithmeticallyFormattedText(solutionOptionBean.getSolutionOptionText()));
                if(solutionOptionBean.getSolution()) {
                    optionRepeaterHTMLContent = optionRepeaterHTMLContent.replace("ASSIGNMENT_QUESTION_OPTION_ANSWER", optionSolutionHTMLContent);
                } else {
                    optionRepeaterHTMLContent = optionRepeaterHTMLContent.replace("ASSIGNMENT_QUESTION_OPTION_ANSWER", "");
                }
                allOptionsContent += optionRepeaterHTMLContent; optionCounter++;
            }
            questionRepeaterHTMLContent = questionRepeaterHTMLContent.replace("ASSIGNMENT_QUESTION_OPTION_REPEATER", allOptionsContent);
            allQuestionsContent += questionRepeaterHTMLContent;
        }
        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("ASSIGNMENT_QUESTION_REPEATER", allQuestionsContent);
        try {
            PrintWriter out = new PrintWriter(generatedHTMLFilePath);
            out.println(mainAssignmentHTMLContent);
            out.close();
            htmlToPdfBean.setHtmlString(mainAssignmentHTMLContent);
            htmlToPdfBean.setAssignmentId(assignmentBean.getAssignmentId());
            htmlToPdfBean.setStatus(1);
            executeShellScript(htmlToPdfBean.getAssignmentId(), "DOWNLOAD_ASSIGNMENT");
        } catch(Exception e) {
            htmlToPdfBean.setMessage(e.getMessage());
        }
        return htmlToPdfBean;
    }

    public HtmlToPdfBean saveAnalysisHtmlFileFromHtmlString(String fileNameWithoutExtension, AssignmentBean assignmentBean) throws IOException {

        HtmlToPdfBean htmlToPdfBean = new HtmlToPdfBean(0, Constants.SOMETHING_WENT_WRONG);
        String OSName = System.getProperty("os.name");

        String templateRootPath = OSName.startsWith("Windows") ? Constants.WINDOWS_ASSIGNMENT_TEMPLATE_PATH : Constants.UBUNTU_ASSIGNMENT_TEMPLATE_PATH;
        String generatedHTMLFileRootPath = OSName.startsWith("Windows") ? Constants.WINDOWS_ASSIGNMENT_FILE_PATH : Constants.UBUNTU_ASSIGNMENT_FILE_PATH;

        File generatedHTMLFileDirectory = new File(generatedHTMLFileRootPath);
        String generatedHTMLFilePath = generatedHTMLFileDirectory.getAbsolutePath() + File.separator + fileNameWithoutExtension + ".html";

        String styleTagHTMLContent = readFile(templateRootPath + "style.html", StandardCharsets.UTF_8);
        String mainAssignmentHTMLContent = readFile(templateRootPath + "analysis.html", StandardCharsets.UTF_8);

        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("STYLE_TAG", styleTagHTMLContent);
        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("ASSIGNMENT_TITLE", assignmentBean.getAssignmentTitle());
        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("ASSIGNMENT_DIVISIONS", StringUtils.collectionToCommaDelimitedString(assignmentBean.getStandardDivisionNames()));
        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("ASSIGNMENT_CHAPTER", assignmentBean.getSubjectChapterName());
        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("ASSIGNMENT_SUBJECT", assignmentBean.getSubject());
        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("ASSIGNMENT_MARKS", String.valueOf(assignmentBean.getMarks()));
        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("ASSIGNMENT_DEADLINE", getFormattedDateString(assignmentBean.getDeadlineDate()));

        List<StudentAssignmentQuestion> studentAssignmentQuestionList = studentAssignmentQuestionService.findByAssignmentId(assignmentBean.getAssignmentId());
        List<QuestionBean> questionBeanList = assignmentBean.getQuestionBeans();
        if(CollectionUtils.isEmpty(studentAssignmentQuestionList)) {
            htmlToPdfBean.setStatus(0);
            htmlToPdfBean.setMessage("No student has attempted this assignment till now.");
            return htmlToPdfBean;
        }
        Map<User, List<StudentAssignmentQuestion>> asignmentQuestionStudentAssignmentQuestionMap = generateStudentAssignmentQuestionMap(studentAssignmentQuestionList, questionBeanList);
        int studentCounter = 0; String tbody = "", theadRepeater = "";
        int numberOfQuestions = assignmentBean.getQuestionBeans().size();
        for(int i = 1; i <= numberOfQuestions; i++) {
            theadRepeater += "<th>Q." + i + "</th>";
        }
        for(User student : asignmentQuestionStudentAssignmentQuestionMap.keySet()) {
            studentCounter++; boolean firstColumn = true;
            String tbodyRepeater = "<tr>";
            for (StudentAssignmentQuestion studentAssignmentQuestion : asignmentQuestionStudentAssignmentQuestionMap.get(student)) {
                if(firstColumn) {
                    tbodyRepeater += "<td>" + studentCounter + "</td>"
                            + "<td>" + student.getName().toUpperCase() + "</td>";
                }
                String isCorrect = studentAssignmentQuestion.getAttempted() ? (studentAssignmentQuestion.getCorrect() ? "Y" : "N") : "S";
                tbodyRepeater += "<td>" + isCorrect + "</td>";
                tbody += tbodyRepeater;
                firstColumn = false;
                tbodyRepeater = "";
            }
            tbody += "</tr>";
        }
        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("THEAD_REPEATER", theadRepeater);
        mainAssignmentHTMLContent = mainAssignmentHTMLContent.replace("TBODY_REPEATER", tbody);

        try {
            PrintWriter out = new PrintWriter(generatedHTMLFilePath);
            out.println(mainAssignmentHTMLContent);
            out.close();
            htmlToPdfBean.setHtmlString(mainAssignmentHTMLContent);
            htmlToPdfBean.setAssignmentId(assignmentBean.getAssignmentId());
            htmlToPdfBean.setStatus(1);
            executeShellScript(htmlToPdfBean.getAssignmentId(), "DOWNLOAD_ASSIGNMENT_ANALYSIS");
        } catch(Exception e) {
            htmlToPdfBean.setMessage(e.getMessage());
        }
        return htmlToPdfBean;
    }

    private Map<User, List<StudentAssignmentQuestion>> generateStudentAssignmentQuestionMap(List<StudentAssignmentQuestion> studentAssignmentQuestionList, List<QuestionBean> questionBeanList) {

        Map<User, List<StudentAssignmentQuestion>> map = new HashMap<>();
        Map<String, Integer> questionOrderMap = new HashMap<>();
        int counter = 1;
        for(QuestionBean questionBean : questionBeanList) {
            questionOrderMap.put(questionBean.getQuestionId(), counter);
            counter++;
        }

        for (StudentAssignmentQuestion studentAssignmentQuestion : studentAssignmentQuestionList) {
            if(map.containsKey(studentAssignmentQuestion.getStudent())) {
                List<StudentAssignmentQuestion> assignmentQuestions = map.get(studentAssignmentQuestion.getStudent());
                if(!assignmentQuestions.contains(studentAssignmentQuestion)) {
                    assignmentQuestions.add(studentAssignmentQuestion);
                    Collections.sort(assignmentQuestions, (o1, o2) -> questionOrderMap.get(o1.getAssignmentQuestion().getQuestion().getId()).compareTo(questionOrderMap.get(o2.getAssignmentQuestion().getQuestion().getId())));
                    map.put(studentAssignmentQuestion.getStudent(), assignmentQuestions);
                }
            } else {
                List<StudentAssignmentQuestion> assignmentQuestions = new ArrayList<>();
                assignmentQuestions.add(studentAssignmentQuestion);
                map.put(studentAssignmentQuestion.getStudent(), assignmentQuestions);
            }
        }

        return map;

    }

    public StatusBean uploadFileHandler(String fileName, String fileType, MultipartFile file) {
        StatusBean statusBean = new StatusBean();
        if (!file.isEmpty()) {
            try {
                String OSName = System.getProperty("os.name");
                byte[] bytes = file.getBytes();
                String rootPath;
                if (fileType.equals("0")) {
                    rootPath = OSName.startsWith("Windows") ? Constants.WINDOWS_STUDENT_DATA_FILE_PATH : Constants.UBUNTU_STUDENT_DATA_FILE_PATH;
                } else if (fileType.equals("1")) {
                    rootPath = OSName.startsWith("Windows") ? Constants.WINDOWS_TEACHER_DATA_FILE_PATH : Constants.UBUNTU_TEACHER_DATA_FILE_PATH;
                } else if (fileType.equals("2")) {
                    rootPath = OSName.startsWith("Windows") ? Constants.WINDOWS_QUESTION_PAPER_FILE_PATH : Constants.UBUNTU_QUESTION_PAPER_FILE_PATH;
                } else {
                    errorLogger.error(TAG + "Tried to send unknown file type : " + fileType);
                    statusBean.setStatus(0);
                    statusBean.setMessage(Constants.FILE_UPLOAD_FAILED);
                    return statusBean;
                }
                File directory = new File(rootPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                // Create the file on server
                File serverFile = new File(directory.getAbsolutePath() + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                if (fileType.equals("0")) {
                    statusBean = userService.readStudentsDataSheet(fileName);

                    if (statusBean.getStatus() == 1) {
                        statusBean.setMessage(Constants.STUDENT_DATA_SHEET_SAVED);
                    }
                } else if (fileType.equals("1")) {
                    //Do Nothing
                    statusBean = userService.readTeachersDataSheet(fileName);

                    if (statusBean.getStatus() == 1) {
                        statusBean.setMessage(Constants.TEACHER_DATA_SHEET_SAVED);
                    }
                } else if (fileType.equals("2")) {
                    statusBean = questionService.readQuestionSheet(fileName);

                    if (statusBean.getStatus() == 1) {
                        statusBean.setMessage(Constants.QUESTION_PAPER_SAVED);
                    }
                }
            } catch (Exception e) {
                errorLogger.error(TAG + "Error in uploading file : " + fileName + " with error : " + e.getMessage());
                statusBean.setStatus(0);
                statusBean.setMessage(Constants.FILE_UPLOAD_FAILED);
            }
        } else {
            errorLogger.error(TAG + "Tried to upload empty file : " + fileName);
            statusBean.setStatus(0);
            statusBean.setMessage(Constants.FILE_UPLOAD_FAILED);
        }
        return statusBean;
    }
}
