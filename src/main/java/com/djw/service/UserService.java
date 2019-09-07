package com.djw.service;

import com.djw.dao.UserMapper;
import com.djw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by MSI on 2019/4/8.
 */

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    Logger logger= LoggerFactory.getLogger(UserService.class);
    public User getUserById(String id){
        return  userMapper.selectByPrimaryKey(id);

    }


    public int login(User user){
        User user1=userMapper.selectByPrimaryKey(user.getId());
        try{
            String pswd=user1.getPassword();
            if(pswd.equals(user.getPassword())){
                return 1;
            }
            else{
                return 0;
            }

        }catch (Exception e)
        {
            logger.error(e.getMessage());
            return 0;
        }
    }

    public int register(User record) {
        System.out.println("serviceimpl:"+record.getId());
        if(userMapper.selectByPrimaryKey(record.getId())!=null)
        {
            logger.info("already exits, fail to register:"+record.getId());
            return 0;
        }
        else
        {
            try{
                int result=userMapper.insertSelective(record);
                if (result==1){
                    return 1;
                }
                else{
                    return 0;
                }
            }catch (Exception e){
                logger.error(e.getMessage());
                return 0;
            }
        }

    }


}
