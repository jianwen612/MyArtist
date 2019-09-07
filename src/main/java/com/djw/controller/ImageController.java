package com.djw.controller;

import com.djw.model.Image;
import com.djw.model.User;
import com.djw.service.ImageService;
import com.djw.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by MSI on 2019/4/9.
 */
@Repository
@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    private static final Log logger = LogFactory.getLog(UserController.class);

    @RequestMapping(value = "/uploadimage.action",method = RequestMethod.POST)
    public int uploadImage(@RequestParam("uploadimage") MultipartFile uploadimage,HttpSession session){
        try {
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/upload";
            User sessionUser=(User)session.getAttribute("user");
            if (sessionUser==null)
            {
                return -1;
            }
            MultipartFile uploadFile = uploadimage;

            Image image = new Image();
            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");//设置日期格式
            String dateString=df.format(new Date());// new Date()为获取当前系统时间
            long t = System.currentTimeMillis();
            Random rd = new Random(t);
            String filename = "用户"+sessionUser.getId()+"-"+dateString+"-"+rd.nextInt()+".png";
            String pathAndName=path +"/" +filename;

            InputStream is = uploadFile.getInputStream();
            // 如果服务器已经存在和上传文件同名的文件，则输出提示信息
            File tempFile = new File(pathAndName);
            File fileParent = tempFile.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            if (tempFile.exists()) {
                boolean delResult = tempFile.delete();
                System.out.println("删除已存在的文件：" + delResult);
            }
            // 开始保存文件到服务器
            if (!filename.equals("")) {
                FileOutputStream fos = new FileOutputStream(pathAndName);
                byte[] buffer = new byte[8192]; // 每次读8K字节
                int count = 0;
                // 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count); // 向服务端文件写入字节流
                }
                fos.close(); // 关闭FileOutputStream对象
                is.close(); // InputStream对象


                image.setId(sessionUser.getId()+"-"+dateString+"-"+rd.nextInt());
                image.setFilepath("upload/"+filename);
                image.setUserId(sessionUser.getId());

                imageService.insertImage(image);
                System.out.println("file name:"+path);

            }
            return 1;
        } catch (IOException f) {
            f.printStackTrace();
            return 0;
        }
    }

    @RequestMapping(value = "/getMyImage.action",method = RequestMethod.GET)
    public List<Image> getMyImage(HttpSession session){
        User user=(User)session.getAttribute("user");
        String userid=user.getId();
        List<Image> myImageList=imageService.getMyImage(userid);
        return myImageList;
    }
}
