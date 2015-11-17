package com.twitter.controller;

import com.twitter.dao.CategoryDao;
import com.twitter.service.Model;
import com.twitter.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by myuzkollar on 27/10/15.
 */
@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    TwitterService twitterService;

    @Autowired
    CategoryDao categoryDao;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public
    @ResponseBody
    Model printWelcome() {
        //model.addAttribute("message", "Hello world!");
        Model model = new Model();
        model.setName("murat");
        return model;
    }

    @RequestMapping(value = "getTwit/{username1}/{username2}/{limit}", method = RequestMethod.GET)
    public
    @ResponseBody
    Integer twitter(@PathVariable String username1, @PathVariable String username2, @PathVariable Integer limit) {
        Integer percent = twitterService.getPercent(username1, username2, limit);
        return percent;
    }


    @RequestMapping(value = "getPerson", method = RequestMethod.GET)
    public
    @ResponseBody
    Integer twitter() {
        return categoryDao.list().size();
    }


}
