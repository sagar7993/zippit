package com.zippit.zippitbackend.controllers;

import com.zippit.zippitbackend.services.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by akash.mercer on 04-Jun-17.
 */

@RestController
@RequestMapping(value = "/standard")
public class StandardController {

    @Autowired
    private StandardService standardService;

}
