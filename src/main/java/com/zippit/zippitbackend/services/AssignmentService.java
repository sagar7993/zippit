package com.zippit.zippitbackend.services;

import com.google.gson.Gson;
import com.zippit.zippitbackend.beans.*;
import com.zippit.zippitbackend.entities.*;
import com.zippit.zippitbackend.enums.UserTypeEnum;
import com.zippit.zippitbackend.repositories.AssignmentRepository;
import com.zippit.zippitbackend.utils.Constants;
import com.zippit.zippitbackend.utils.NotificationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by akash.mercer on 04-Jun-17.
 */

@Service
public class AssignmentService {
    private static final String TAG = "AssignmentService : ";

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AssignmentStandardDivisionService assignmentStandardDivisionService;

    @Autowired
    private AssignmentQuestionService assignmentQuestionService;

    @Autowired
    private SubjectChapterService subjectChapterService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private StandardDivisionService standardDivisionService;

    @Autowired
    private StudentAssignmentService studentAssignmentService;

    @Autowired
    private SolutionOptionService solutionOptionService;

    @Autowired
    private StudentAssignmentQuestionService studentAssignmentQuestionService;

    @Autowired
    private TeacherInstituteService teacherInstituteService;

    @Autowired
    private EntityManager entityManager;

    private static Logger debugLogger = LoggerFactory.getLogger("debugLogs");

    private static Logger errorLogger = LoggerFactory.getLogger("errorLogs");

    public void saveAll(List<Assignment> assignments) throws Exception {
        assignmentRepository.save(assignments);
    }

    public AssignmentsListBean findByUser(String userId) {
        AssignmentsListBean assignmentsListBean = new AssignmentsListBean();
        User retrievedUser = userService.findById(userId);
        if (retrievedUser == null) {
            errorLogger.error(TAG + "Error in finding User with id : " + userId);
            assignmentsListBean.setStatus(0);
            assignmentsListBean.setMessage(Constants.SOMETHING_WENT_WRONG);
            return assignmentsListBean;
        }
        List<AssignmentBean> assignmentBeanList = assignmentRepository.findByTeacher(retrievedUser);
        for(AssignmentBean assignmentBean : assignmentBeanList) {
            List<String> standardDivisionNames = new ArrayList<>();
            List<AssignmentStandardDivision> assignmentStandardDivisionList = assignmentStandardDivisionService.findByAssignmentId(assignmentBean.getAssignmentId());
            for(AssignmentStandardDivision assignmentStandardDivision : assignmentStandardDivisionList) {
                standardDivisionNames.add(assignmentStandardDivision.getStandardDivision().getStandard().getStandardName() + " " + assignmentStandardDivision.getStandardDivision().getDivisionName());
            }
            assignmentBean.setStandardDivisionNames(standardDivisionNames);
        }
        assignmentsListBean.setAssignmentsList(assignmentBeanList);
        assignmentsListBean.setStatus(1);
        return assignmentsListBean;
    }

    public AssignmentBean findByAssignment(String assignmentId) {
        AssignmentBean retrievedAssignment = assignmentRepository.findById(assignmentId);
        if (retrievedAssignment == null) {
            errorLogger.error(TAG + "Error in finding Assignment with id : " + assignmentId);
            retrievedAssignment.setStatus(0);
            retrievedAssignment.setMessage(Constants.SOMETHING_WENT_WRONG);
            return retrievedAssignment;
        }
        List<QuestionBean> questionBeanList = assignmentQuestionService.findByAssignment(retrievedAssignment.getAssignmentId());
        if(CollectionUtils.isEmpty(questionBeanList)) {
            errorLogger.error(TAG + "Error in finding QuestionBeans for assignment with id : " + assignmentId);
            retrievedAssignment.setStatus(0);
            retrievedAssignment.setMessage(Constants.SOMETHING_WENT_WRONG);
            return retrievedAssignment;
        }
        retrievedAssignment.setQuestionBeans(questionBeanList);
        List<String> standardDivisionNames = new ArrayList<>();
        List<AssignmentStandardDivision> assignmentStandardDivisionList = assignmentStandardDivisionService.findByAssignmentId(retrievedAssignment.getAssignmentId());
        for(AssignmentStandardDivision assignmentStandardDivision : assignmentStandardDivisionList) {
            standardDivisionNames.add(assignmentStandardDivision.getStandardDivision().getStandard().getStandardName() + " " + assignmentStandardDivision.getStandardDivision().getDivisionName());
        }
        retrievedAssignment.setStandardDivisionNames(standardDivisionNames);
        retrievedAssignment.setStatus(1);
        return retrievedAssignment;
    }

    public AssignmentsBean findByUserAndSubjectChapter(String userId, String subjectChapterId, Pageable pageable) {
        AssignmentsBean assignmentsBean = new AssignmentsBean();

        User retrievedUser = userService.findById(userId);

        if (retrievedUser == null) {
            errorLogger.error(TAG + "Error in finding User with id : " + userId);

            assignmentsBean.setStatus(0);
            assignmentsBean.setMessage(Constants.SOMETHING_WENT_WRONG);

            return assignmentsBean;
        }

        SubjectChapter retrievedSubjectChapter = subjectChapterService.findById(subjectChapterId);

        if (retrievedSubjectChapter == null) {
            errorLogger.error(TAG + "Error in finding SubjectChapter with id : " + subjectChapterId);

            assignmentsBean.setStatus(0);
            assignmentsBean.setMessage(Constants.SOMETHING_WENT_WRONG);

            return assignmentsBean;
        }

        Page<AssignmentBean> assignmentsPage;

        if (UserTypeEnum.STUDENT.getType() == retrievedUser.getUserType().getType()) {
            assignmentsPage = assignmentStandardDivisionService.findByStandardDivisionAndSubjectChapter(retrievedUser.getStandardDivision(), retrievedSubjectChapter, pageable);

            List<StudentAssignmentBean> studentAssignmentBeans = studentAssignmentService.findByStudent(retrievedUser);

            Map<String, StudentAssignmentBean> studentAssignmentBeanMap = new HashMap<>();

            for (StudentAssignmentBean studentAssignmentBean : studentAssignmentBeans) {
                studentAssignmentBeanMap.put(studentAssignmentBean.getAssignmentId(), studentAssignmentBean);
            }

            for (AssignmentBean assignmentBean : assignmentsPage.getContent()) {
                String key = assignmentBean.getAssignmentId();

                if (studentAssignmentBeanMap.containsKey(key)) {
                    assignmentBean.setStudentAssignmentBean(studentAssignmentBeanMap.get(key));
                    assignmentBean.setSolved(true);

                    continue;
                } else {
                    if (System.currentTimeMillis() >= assignmentBean.getDeadlineDate()) {
                        assignmentBean.setExpired(true);
                    }
                }
            }
        } else {
            TeacherInstitute retrievedTeacherInstitute = teacherInstituteService.findByTeacher(retrievedUser);

            if (retrievedTeacherInstitute == null) {
                errorLogger.error(TAG + "Error in finding TeacherInstitute with teacherId : " + userId);

                assignmentsBean.setStatus(0);
                assignmentsBean.setMessage(Constants.SOMETHING_WENT_WRONG);

                return assignmentsBean;
            }

            assignmentsPage = assignmentStandardDivisionService.findByInstituteAndSubjectChapter(retrievedTeacherInstitute.getInstitute(), retrievedSubjectChapter, pageable);
        }

        assignmentsBean.setAssignmentsPage(assignmentsPage);

        assignmentsBean.setStatus(1);
        return assignmentsBean;
    }

    public AssignmentDetailsBean findById(String userId, String assignmentId) {
        AssignmentDetailsBean assignmentDetailsBean = new AssignmentDetailsBean();

        User retrievedUser = userService.findById(userId);

        if (retrievedUser == null) {
            errorLogger.error(TAG + "Error in finding User with id : " + userId);

            assignmentDetailsBean.setStatus(0);
            assignmentDetailsBean.setMessage(Constants.SOMETHING_WENT_WRONG);

            return assignmentDetailsBean;
        }

        Assignment retrievedAssignment = assignmentRepository.findOne(assignmentId);

        if (retrievedAssignment == null) {
            errorLogger.error(TAG + "Error in finding Assignment with id : " + assignmentId);

            assignmentDetailsBean.setStatus(0);
            assignmentDetailsBean.setMessage(Constants.SOMETHING_WENT_WRONG);

            return assignmentDetailsBean;
        }

        AssignmentBean assignmentBean = new AssignmentBean(retrievedAssignment);
        assignmentBean.setQuestionBeans(assignmentQuestionService.findByAssignment(retrievedAssignment.getId()));

        if (UserTypeEnum.STUDENT.getType() == retrievedUser.getUserType().getType()) {
            assignmentBean.setStudentAssignmentBean(studentAssignmentService.findByStudentAndAssignment(retrievedUser, retrievedAssignment));

            if (assignmentBean.getStudentAssignmentBean() != null) {
                if (StringUtils.isEmpty(assignmentBean.getStudentAssignmentBean().getStudentSolution())) {
                    assignmentBean.setExpired(true);
                } else {
                    assignmentBean.setSolved(true);
                }

                SubmitAssignmentBean submitAssignmentBean = new Gson().fromJson(assignmentBean.getStudentAssignmentBean().getStudentSolution(), SubmitAssignmentBean.class);

                if (submitAssignmentBean != null) {
                    Map<String, String> studentSolutionMap = new HashMap<>();

                    for (QuestionSolutionBean questionSolutionBean : submitAssignmentBean.getQuestionSolutionBeans()) {
                        studentSolutionMap.put(questionSolutionBean.getQuestionId(), questionSolutionBean.getSolutionOptionId());
                    }

                    for (QuestionBean questionBean : assignmentBean.getQuestionBeans()) {
                        for (SolutionOptionBean solutionOptionBean : questionBean.getSolutionOptionBeans()) {
                            if (solutionOptionBean.getSolutionOptionId().equals(studentSolutionMap.get(questionBean.getQuestionId()))) {
                                solutionOptionBean.setSelectedSolution(true);
                                questionBean.setSolved(true);

                                break;
                            }
                        }
                    }
                }
            } else {
                StudentAssignment studentAssignment = new StudentAssignment();
                studentAssignment.setStartDate(System.currentTimeMillis());
                studentAssignment.setStudent(retrievedUser);
                studentAssignment.setAssignment(retrievedAssignment);

                try {
                    studentAssignmentService.save(studentAssignment);

                    assignmentBean.setStudentAssignmentBean(new StudentAssignmentBean(studentAssignment));
                } catch (Exception e) {
                    errorLogger.error(TAG + "Error in creating StudentAssignment with error : " + e.getMessage());

                    assignmentDetailsBean.setStatus(0);
                    assignmentDetailsBean.setMessage(Constants.SOMETHING_WENT_WRONG);

                    return assignmentDetailsBean;
                }

                if (System.currentTimeMillis() >= assignmentBean.getDeadlineDate()) {
                    assignmentBean.setExpired(true);
                }
            }
        }

        assignmentDetailsBean.setAssignmentBean(assignmentBean);

        assignmentDetailsBean.setStatus(1);
        return assignmentDetailsBean;
    }

    @Transactional
    public StatusBean submit(SubmitAssignmentBean submitAssignmentBean) {
        StatusBean statusBean = new StatusBean();

        StudentAssignment retrievedStudentAssignment = studentAssignmentService.findById(submitAssignmentBean.getStudentAssignmentId());

        if (retrievedStudentAssignment == null) {
            errorLogger.error(TAG + "Error in finding StudentAssignment with id : " + submitAssignmentBean.getStudentAssignmentId());

            statusBean.setStatus(0);
            statusBean.setMessage(Constants.SOMETHING_WENT_WRONG);

            return statusBean;
        }

        List<AssignmentQuestion> assignmentQuestions = assignmentQuestionService.findByAssignment(retrievedStudentAssignment.getAssignment());

        List<StudentAssignmentQuestion> studentAssignmentQuestions = new ArrayList<>();

        for (AssignmentQuestion assignmentQuestion : assignmentQuestions) {
            StudentAssignmentQuestion studentAssignmentQuestion = new StudentAssignmentQuestion();
            studentAssignmentQuestion.setAssignmentQuestion(assignmentQuestion);
            studentAssignmentQuestion.setStudent(retrievedStudentAssignment.getStudent());

            studentAssignmentQuestions.add(studentAssignmentQuestion);
        }

        retrievedStudentAssignment.setAttempted(submitAssignmentBean.getQuestionSolutionBeans().size());

        if (!CollectionUtils.isEmpty(submitAssignmentBean.getQuestionSolutionBeans())) {
            List<String> questionIds = new ArrayList<>();

            for (QuestionSolutionBean questionSolutionBean : submitAssignmentBean.getQuestionSolutionBeans()) {
                questionIds.add(questionSolutionBean.getQuestionId());
            }

            List<SolutionOptionBean> solutionOptionBeans = solutionOptionService.findByQuestionIdsWithSolution(questionIds);

            Map<String, StudentAssignmentQuestion> studentAssignmentQuestionMap = new HashMap<>();

            for (StudentAssignmentQuestion studentAssignmentQuestion : studentAssignmentQuestions) {
                studentAssignmentQuestionMap.put(studentAssignmentQuestion.getAssignmentQuestion().getQuestion().getId(), studentAssignmentQuestion);
            }

            Map<String, SolutionOptionBean> solutionOptionBeanMap = new HashMap<>();

            for (SolutionOptionBean solutionOptionBean : solutionOptionBeans) {
                solutionOptionBeanMap.put(solutionOptionBean.getQuestionId(), solutionOptionBean);
            }

            int marks = 0;

            int correct = 0;

            for (QuestionSolutionBean questionSolutionBean : submitAssignmentBean.getQuestionSolutionBeans()) {
                String key = questionSolutionBean.getQuestionId();

                StudentAssignmentQuestion studentAssignmentQuestion = studentAssignmentQuestionMap.get(key);
                studentAssignmentQuestion.setAttempted(true);

                if (solutionOptionBeanMap.get(key).getSolutionOptionId().equals(questionSolutionBean.getSolutionOptionId())) {
                    studentAssignmentQuestion.setCorrect(true);

                    marks += solutionOptionBeanMap.get(key).getMarks();
                    correct++;
                }
            }

            retrievedStudentAssignment.setMarks(marks);
            retrievedStudentAssignment.setCorrect(correct);
        } else {
            retrievedStudentAssignment.setMarks(0);
            retrievedStudentAssignment.setCorrect(0);
        }

        retrievedStudentAssignment.setStudentSolution(new Gson().toJson(submitAssignmentBean));
        retrievedStudentAssignment.setSubmitDate(System.currentTimeMillis());

        try {
            studentAssignmentQuestionService.saveAll(studentAssignmentQuestions);

            try {
                entityManager.merge(retrievedStudentAssignment);
            } catch (Exception e) {
                errorLogger.error(TAG + "Error in updating StudentAssignment with id : " + submitAssignmentBean.getStudentAssignmentId() + " with error : " + e.getMessage());

                statusBean.setStatus(0);
                statusBean.setMessage(Constants.SOMETHING_WENT_WRONG);

                return statusBean;
            }
        } catch (Exception e) {
            errorLogger.error(TAG + "Error in saving StudentAssignmentQuestions for StudentAssignment with id : " + submitAssignmentBean.getStudentAssignmentId() + " with error : " + e.getMessage());

            statusBean.setStatus(0);
            statusBean.setMessage(Constants.SOMETHING_WENT_WRONG);

            return statusBean;
        }

        statusBean.setStatus(1);
        return statusBean;
    }

    @Transactional(rollbackFor = {Exception.class})
    public StatusBean save(AssignmentBean assignmentBean) {
        StatusBean statusBean = new StatusBean();

        User retrievedTeacher = userService.findById(assignmentBean.getTeacherId());

        if (retrievedTeacher == null) {
            errorLogger.error(TAG + "Error in finding Teacher with id : " + assignmentBean.getTeacherId());

            statusBean.setStatus(0);
            statusBean.setMessage(Constants.SOMETHING_WENT_WRONG);

            return statusBean;
        }

        SubjectChapter retrievedSubjectChapter = subjectChapterService.findById(assignmentBean.getSubjectChapterId());

        if (retrievedSubjectChapter == null) {
            errorLogger.error(TAG + "Error in finding SubjectChapter with id : " + assignmentBean.getSubjectChapterId());

            statusBean.setStatus(0);
            statusBean.setMessage(Constants.SOMETHING_WENT_WRONG);

            return statusBean;
        }

        Assignment assignment = new Assignment();
        assignment.setAssignmentTitle(assignmentBean.getAssignmentTitle());
        assignment.setDeadlineDate(assignmentBean.getDeadlineDate());
        assignment.setMarks((assignmentBean.getMarks()));
        assignment.setActive(true);
        assignment.setSubjectChapter(retrievedSubjectChapter);
        assignment.setTeacher(retrievedTeacher);

        List<Question> questions = questionService.findByIds(assignmentBean.getQuestionIds());

        if (CollectionUtils.isEmpty(questions)) {
            errorLogger.error(TAG + "Error in finding Questions with ids : " + assignmentBean.getQuestionIds());

            statusBean.setStatus(0);
            statusBean.setMessage(Constants.SOMETHING_WENT_WRONG);

            return statusBean;
        }

        int marks = 0;

        List<StandardDivision> standardDivisions = standardDivisionService.findByIds(assignmentBean.getStandardDivisionIds());

        if (CollectionUtils.isEmpty(standardDivisions)) {
            errorLogger.error(TAG + "Error in finding StandardDivisions with ids : " + assignmentBean.getStandardDivisionIds());

            statusBean.setStatus(0);
            statusBean.setMessage(Constants.SOMETHING_WENT_WRONG);

            return statusBean;
        }

        Integer studentCount = userService.findCountByStandardDivisions(standardDivisions);

        assignment.setTotal(studentCount);

        List<AssignmentQuestion> assignmentQuestions = new ArrayList<>();

        for (Question question : questions) {
            marks += question.getMarks();

            AssignmentQuestion assignmentQuestion = new AssignmentQuestion();
            assignmentQuestion.setAssignment(assignment);
            assignmentQuestion.setQuestion(question);

            assignmentQuestions.add(assignmentQuestion);
        }

        assignment.setMarks(marks);

        List<AssignmentStandardDivision> assignmentStandardDivisions = new ArrayList<>();

        for (StandardDivision standardDivision : standardDivisions) {
            AssignmentStandardDivision assignmentStandardDivision = new AssignmentStandardDivision();
            assignmentStandardDivision.setAssignment(assignment);
            assignmentStandardDivision.setStandardDivision(standardDivision);

            assignmentStandardDivisions.add(assignmentStandardDivision);
        }

        try {
            assignmentRepository.save(assignment);

            try {
                assignmentQuestionService.saveAll(assignmentQuestions);
            } catch (Exception e) {
                errorLogger.error(TAG + "Error in saving AssignmentQuestions with error : " + e.getMessage());

                statusBean.setStatus(0);
                statusBean.setMessage(Constants.SOMETHING_WENT_WRONG);

                throw e;
            }

            try {
                assignmentStandardDivisionService.saveAll(assignmentStandardDivisions);
            } catch (Exception e) {
                errorLogger.error(TAG + "Error in saving AssignmentStandardDivisions with error : " + e.getMessage());

                statusBean.setStatus(0);
                statusBean.setMessage(Constants.SOMETHING_WENT_WRONG);

                throw e;
            }
        } catch (Exception e) {
            errorLogger.error(TAG + "Error in saving Assignment with error : " + e.getMessage());

            statusBean.setStatus(0);
            statusBean.setMessage(Constants.SOMETHING_WENT_WRONG);

            throw e;
        }

        //Send Notifications to Students
        sendAssignmentPublishedNotification(assignment, standardDivisions);

        statusBean.setStatus(1);
        return statusBean;
    }

    @Deprecated
    public StandardsBean getStandardsFromAssignmentsByTeacher(String teacherId) {
        StandardsBean standardsBean = new StandardsBean();

        User retrievedTeacher = userService.findById(teacherId);

        if (retrievedTeacher == null) {
            errorLogger.error(TAG + "Error in finding Teacher with id : " + teacherId);

            standardsBean.setStatus(0);
            standardsBean.setMessage(Constants.SOMETHING_WENT_WRONG);

            return standardsBean;
        }

        standardsBean.setStandardBeans(assignmentStandardDivisionService.getStandardsFromAssignmentsByTeacher(retrievedTeacher));
        standardsBean.setStatus(1);

        return standardsBean;
    }

    public List<SubjectChapterBean> findSubjectChaptersFromAssignmentsByTeacher(User teacher) {
        return assignmentRepository.findSubjectChaptersFromAssignmentsByTeacher(teacher);
    }

    public TeacherAssignmentSummaryBean getAssignmentSummary(String assignmentId) {
        TeacherAssignmentSummaryBean teacherAssignmentSummaryBean = new TeacherAssignmentSummaryBean();

        Assignment retrievedAssignment = assignmentRepository.findOne(assignmentId);

        if (retrievedAssignment == null) {
            errorLogger.error(TAG + "Error in finding Assignment with id : " + assignmentId);

            teacherAssignmentSummaryBean.setStatus(0);
            teacherAssignmentSummaryBean.setMessage(Constants.SOMETHING_WENT_WRONG);

            return teacherAssignmentSummaryBean;
        }

        teacherAssignmentSummaryBean.setQuestionSolutionSummaryBeans(studentAssignmentQuestionService.getAssignmentSummary(retrievedAssignment));

        teacherAssignmentSummaryBean.setStatus(1);
        return teacherAssignmentSummaryBean;
    }

    private void sendAssignmentPublishedNotification(Assignment assignment, List<StandardDivision> standardDivisions) {
        NotificationBean notificationBean = new NotificationBean();
        notificationBean.setTitle("New assignment published!");
        notificationBean.setShortDescription(assignment.getAssignmentTitle() + " published for " + assignment.getSubjectChapter().getSubjectChapterName());
        notificationBean.setLongDescription("Hey there, we thought to give you heads up about the new assignment titled \"" + assignment.getAssignmentTitle() + "\" published for " + assignment.getSubjectChapter().getSubjectChapterName() + " chapter.");
        notificationBean.setPromoUrl(assignment.getSubjectChapter().getId());
        notificationBean.setNotificationType(1);

        List<String> receivers = userService.fetchFcmTokensByStandardDivisions(standardDivisions);

        NotificationManager.sendBroadcastNotification(notificationBean, receivers);
    }

}
