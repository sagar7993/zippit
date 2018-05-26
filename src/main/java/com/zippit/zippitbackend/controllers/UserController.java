package com.zippit.zippitbackend.controllers;

import com.zippit.zippitbackend.beans.*;
import com.zippit.zippitbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by akash.mercer on 04-Jun-17.
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody LoginResultBean signUp(@RequestBody SignUpBean signUpBean) {
        return userService.signUp(signUpBean);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody LoginResultBean login(@RequestBody LoginBean loginBean) {
        return userService.login(loginBean);
    }

    @RequestMapping(value = "/resetCredentials", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    StatusBean resetCredentials(@RequestBody CredentialBean credentialBean) {
        return userService.resetCredentials(credentialBean);
    }

    @RequestMapping(value = "/updateNotificationToken", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody StatusBean updateNotificationToken(@RequestBody NotificationTokenBean notificationTokenBean) {
        return userService.updateNotificationToken(notificationTokenBean);
    }

}
