package com.zippit.zippitbackend.utils;

/**
 * Created by akash.mercer on 21-Jun-17.
 */
public class Constants {

    public static final String MIX_PANEL_API_TOKEN = "bb442cafc3a156fce5ba084bcd1872da";

    static final String FCM_KEY = "AIzaSyBGc9SHQ8tzwqv2XPHAT4wcDjjWylAxOUo";

    //Response URL's
    public static final String LOCALHOST_URL = "http://localhost:8000/#/";
    public static final String DEV_URL = "http://dev.sumzupp.com/admin/#/";
    public static final String PROD_URL = "http://prod.sumzupp.com/admin/#/";

    //Error Messages
    public static final String SOMETHING_WENT_WRONG = "Something went wrong. Please try again later.";
    public static final String DATABASE_DUMP_PATH = "/home/ubuntu/dump/";
    public static final String UBUNTU_STUDENT_DATA_FILE_PATH = "/home/ubuntu/student_data/";
    public static final String WINDOWS_STUDENT_DATA_FILE_PATH = "\\Work\\SumzuppFiles\\student_data\\";
    public static final String UBUNTU_TEACHER_DATA_FILE_PATH = "/home/ubuntu/teacher_data/";
    public static final String WINDOWS_TEACHER_DATA_FILE_PATH = "\\Work\\SumzuppFiles\\teacher_data\\";
    public static final String UBUNTU_QUESTION_PAPER_FILE_PATH = "/home/ubuntu/question_sheets/";
    public static final String WINDOWS_QUESTION_PAPER_FILE_PATH = "\\Work\\SumzuppFiles\\question_sheets\\";
    public static final String UBUNTU_STUDENT_WELCOME_PATH = "/home/ubuntu/students/welcome/";
    public static final String WINDOWS_STUDENT_WELCOME_PATH = "\\Work\\SumzuppFiles\\students\\welcome\\";
    public static final String UBUNTU_ASSIGNMENT_FILE_PATH = "/home/ubuntu/assignment/";
    public static final String WINDOWS_ASSIGNMENT_FILE_PATH = "\\Work\\SumzuppFiles\\assignment\\";
    public static final String UBUNTU_ASSIGNMENT_TEMPLATE_PATH = "/home/ubuntu/templates/assignment/";
    public static final String WINDOWS_ASSIGNMENT_TEMPLATE_PATH = "\\Work\\SumzuppFiles\\templates\\assignment\\";

    //Success Messages
    public static final String POST_SAVED = "Comment has been successfully saved.";
    public static final String UPVOTED = "Upvoted";
    public static final String DOWNVOTED = "Downvoted";
    public static final String LIKED = "Liked";
    public static final String UNLIKED = "Unliked";
    public static final String VOTED = "Voted";
    public static final String COMMENT_SAVED = "Comment has been successfully saved.";
    public static final String STUDENT_DATA_SHEET_SAVED = "Student data sheet successfully saved.";
    public static final String TEACHER_DATA_SHEET_SAVED = "Teacher data sheet successfully saved.";
    public static final String QUESTION_PAPER_SAVED = "Question paper successfully saved.";

    //Failure Messages
    public static final String CONTACT_NUMBER_ALREADY_EXISTS = "User with entered contact number already exists. Please login to continue.";
    public static final String EMAIL_ALREADY_EXISTS = "User with entered email already exists. Please login to continue.";
    public static final String WRONG_CREDENTIALS = "Username and password combination do not match. Please contact your school administration for your account details.";
    public static final String USER_NOT_REGISTERED = "User with entered username and password does not exist. Please contact your school administration for your account details.";
    public static final String FILE_UPLOAD_FAILED = "File upload failed";

    //Miscellaneous
    public static final String ACCOUNT_TYPE_EMAIL = "Email";
    public static final String DEVICE_TYPE_ANDROID = "Android";
    public static final String DEVICE_TYPE_IOS = "iOS";

    //Failure Message
    public static final String INVALID_ADMIN_CREDENTIALS = "Incorrect Email / Password combination";

    //Success Message
    public static final String SIGN_UP_SUCCESSFUL  = "Signup successful";
    public static final String LOGIN_SUCCESSFUL  = "Login successful";

    //Mails
    public static final String FROM_EMAIL = "sumzupp@gmail.com";
    public static final String REPLY_TO_EMAIL = "sumzupp@gmail.com";
}
