package com.zippit.zippitbackend.utils;

import java.text.DecimalFormat;

/**
 * Created by akash.mercer on 21-Jun-17.
 */
public class Commons {

    public static DecimalFormat amountDecimalFormat = new DecimalFormat("#.##");

    public static String getResponseBaseUrl(String requestUrl) {

        if(requestUrl.indexOf("localhost") > 0) {
            return Constants.LOCALHOST_URL;
        } else if(requestUrl.indexOf("dev.sumzupp.com") > 0) {
            return Constants.DEV_URL;
        } else {
            return Constants.PROD_URL;
        }

    }

    public static String getRedirectUrlByRequestUrlAndUploadedFileType(String requestUrl, String fileType) {

        String responseBaseUrl = getResponseBaseUrl(requestUrl);

        if (fileType.equals("0")) {
            return responseBaseUrl + "uploadStudentData";
        } else if (fileType.equals("1")) {
            return responseBaseUrl + "uploadTeacherData";
        } else if (fileType.equals("2")) {
            return responseBaseUrl + "uploadQuestionSheet";
        } else {
            return responseBaseUrl + "uploadQuestionSheet";
        }

    }

}
