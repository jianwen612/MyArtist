package com.djw.service;

import com.djw.dao.ImageMapper;
import com.djw.dao.UserMapper;
import com.djw.model.Image;
import com.djw.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MSI on 2019/4/8.
 */

@Service
public class ImageService {
    @Autowired
    ImageMapper imageMapper;
    Logger logger= LoggerFactory.getLogger(ImageService.class);

    public int insertImage(Image image)
    {
        try {
            imageMapper.insertSelective(image);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }


    public List<Image> getMyImage(String userid) {
        List<Image> imageList=imageMapper.selectByUserid(userid);
        System.out.println("获取图片中："+imageList.size());
        return imageList;
    }
}
