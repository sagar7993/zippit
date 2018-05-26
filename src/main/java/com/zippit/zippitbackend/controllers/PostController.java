package com.zippit.zippitbackend.controllers;

import com.zippit.zippitbackend.beans.PostBean;
import com.zippit.zippitbackend.beans.PostVoteBean;
import com.zippit.zippitbackend.beans.StatusBean;
import com.zippit.zippitbackend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by akash.mercer on 04-Jun-17.
 */

@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody StatusBean save(@RequestBody PostBean postBean) {
        return postService.save(postBean);
    }

    @RequestMapping(value = "/vote", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody StatusBean vote(@RequestBody PostVoteBean postVoteBean) {
        return postService.vote(postVoteBean);
    }
}
