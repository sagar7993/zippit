package com.zippit.zippitbackend.controllers;

import com.zippit.zippitbackend.beans.LoginBean;
import com.zippit.zippitbackend.beans.LoginResultBean;
import com.zippit.zippitbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by akash.mercer on 08-Jun-17.
 */

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/webTeacherLogin", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody LoginResultBean webTeacherLogin(@RequestBody LoginBean loginBean){
        return userService.webTeacherLogin(loginBean);
    }

}
