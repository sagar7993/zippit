package com.zippit.zippitbackend.beans;

/**
 * Created by Sagar Jain on 8/6/2017.
 */
public class HtmlToPdfBean extends StatusBean {

    private String assignmentId;
    private String htmlString;
    private String htmlFilePath;
    private String pdfFilePath;
    private String pdfBase64String;
    private String pdfDownloadUrl;

    public HtmlToPdfBean(Integer status, String message) {
        super(status, message);
    }

    public HtmlToPdfBean() {
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getHtmlString() {
        return htmlString;
    }

    public void setHtmlString(String htmlString) {
        this.htmlString = htmlString;
    }

    public String getHtmlFilePath() {
        return htmlFilePath;
    }

    public void setHtmlFilePath(String htmlFilePath) {
        this.htmlFilePath = htmlFilePath;
    }

    public String getPdfFilePath() {
        return pdfFilePath;
    }

    public void setPdfFilePath(String pdfFilePath) {
        this.pdfFilePath = pdfFilePath;
    }

    public String getPdfBase64String() {
        return pdfBase64String;
    }

    public void setPdfBase64String(String pdfBase64String) {
        this.pdfBase64String = pdfBase64String;
    }

    public String getPdfDownloadUrl() {
        return pdfDownloadUrl;
    }

    public void setPdfDownloadUrl(String pdfDownloadUrl) {
        this.pdfDownloadUrl = pdfDownloadUrl;
    }
}