package com.zippit.zippitbackend.controllers;

import com.zippit.zippitbackend.beans.AdminLoginStatusBean;
import com.zippit.zippitbackend.services.AdminService;
import com.zippit.zippitbackend.beans.LoginBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by akash.mercer on 08-Jun-17.
 */

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    AdminLoginStatusBean login(@RequestBody LoginBean loginBean){
        return adminService.login(loginBean);
    }

}
