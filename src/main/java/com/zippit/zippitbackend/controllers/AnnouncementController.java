package com.zippit.zippitbackend.controllers;

import com.zippit.zippitbackend.services.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by akash.mercer on 04-Jun-17.
 */

@RestController
@RequestMapping(value = "/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

}
