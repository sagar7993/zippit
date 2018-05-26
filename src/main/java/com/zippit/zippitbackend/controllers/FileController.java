package com.zippit.zippitbackend.controllers;

import com.zippit.zippitbackend.utils.Commons;
import com.zippit.zippitbackend.beans.HtmlToPdfBean;
import com.zippit.zippitbackend.beans.StatusBean;
import com.zippit.zippitbackend.services.FileService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

/**
 * Created by akash.mercer on 24-Jul-17.
 */

@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody RedirectView uploadFileHandler(@RequestParam("fileName") String fileName, @RequestParam("fileType") String fileType, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        StatusBean statusBean = fileService.uploadFileHandler(fileName, fileType, file);
        String requestUrl = request.getRequestURL().toString();
        String redirectUrl = Commons.getRedirectUrlByRequestUrlAndUploadedFileType(requestUrl, fileType);
        String redirectUrlWithQueryParameters = redirectUrl + "?status=" + statusBean.getStatus() + "&message=" + statusBean.getMessage();
        return new RedirectView(redirectUrlWithQueryParameters);
    }

    @RequestMapping(value = "/getAssignmentHtml/{assignmentId}", method = RequestMethod.GET)
    public @ResponseBody HtmlToPdfBean getAssignmentHtml(@PathVariable("assignmentId") String assignmentId) throws IOException {
        return fileService.getAssignmentHtml(assignmentId);
    }

    @RequestMapping(value = "/getAssignmentAnalysisHtml/{assignmentId}", method = RequestMethod.GET)
    public @ResponseBody HtmlToPdfBean getAssignmentAnalysisHtml(@PathVariable("assignmentId") String assignmentId) throws IOException {
        return fileService.getAssignmentAnalysisHtml(assignmentId);
    }

    @RequestMapping(value = "/sendAssignmentAnalysisPdfEmail/{assignmentId}/{teacherId}", method = RequestMethod.GET)
    public @ResponseBody StatusBean sendAssignmentAnalysisPdfEmail(@PathVariable("assignmentId") String assignmentId, @PathVariable("teacherId") String teacherId) throws IOException {
        return fileService.sendAssignmentAnalysisPdfEmail(assignmentId, teacherId);
    }

}
