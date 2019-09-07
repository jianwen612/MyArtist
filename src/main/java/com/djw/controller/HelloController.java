package com.djw.controller;

import com.djw.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MSI on 2019/4/8.
 */
@RestController
public class HelloController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String say(){

        return "hello";
    }


}
