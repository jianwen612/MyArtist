package com.djw.controller;

import com.djw.model.User;
import com.djw.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by MSI on 2019/4/8.
 */
@Repository
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    private static final Log logger = LogFactory.getLog(UserController.class);
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String say(){
        return "hello";
    }


    @RequestMapping(value = "/userid", method = RequestMethod.POST)
    public String getNameById(@RequestParam("id") String id){
        String name="";
        User user= userService.getUserById(id);
        name=user.getUsername();
        return name;
    }

    @RequestMapping (value = "/login.action")
    @ResponseBody
    public int login(/*@RequestBody*/ User user,  HttpSession session)
    {
        User sessionUser=(User)session.getAttribute("user");
        String userId=user.getId();
        System.out.println("try login:"+user);
        System.out.println("tri loginsession:"+sessionUser);
        if(user.getId()==null||user.getPassword()==null)
        {
            System.out.println("some is null");
            return 0;
        }
        if(sessionUser==null)
        {
            System.out.println("session==null");
            int result=userService.login(user);
            System.out.println("登录失败");
            if(result==1)
            {
                User findUser=userService.getUserById(userId);
                session.setAttribute("user", findUser);
                return 1;
            }
            else{
                return 0;
            }
        }else{

            System.out.println("user reloginUser:"+sessionUser);
            return 1;
        }
    }

    @RequestMapping (value = "/logout.action",method = RequestMethod.POST)
    @ResponseBody
    public  String logout(HttpSession session){
        System.out.println("controller-user-logout:");
        session.invalidate();
        return "1";
    }

    @RequestMapping (value = "/register.action",method = RequestMethod.POST)
    @ResponseBody
    public int register(User user,HttpSession session){
        if(user==null){
            return 0;
        }
        logger.info("controller-user-register:"+user.toString());
        try{
            int status=userService.register(user)==1?1:0;
            return  status;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }


}
