package com.ydl.api;

import com.alibaba.fastjson.JSONObject;
import com.ydl.annotation.UserLoginToken;
import com.ydl.entity.PicUploadResult;
import com.ydl.service.PicUploadFileSystemService;
import com.ydl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("Pic")
public class PicUploadApi {

    @Autowired
    private PicUploadFileSystemService picUploadFileSystemService;
    //    @Autowired
//    UserDao userDao;
    @Autowired
    UserService userService;

    @UserLoginToken
    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile uploadFile, @RequestParam("username") String username) throws Exception {
        JSONObject jsonObject = new JSONObject();
        PicUploadResult picUploadResult = picUploadFileSystemService.upload(uploadFile, username);
        if (picUploadResult.getStatus().equals("noexpected")) {
            jsonObject.put("message", "请上传符合格式的图片");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        if (picUploadResult.getStatus().equals("error")) {
            jsonObject.put("message", "上传失败");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        if (userService.findByUsername(username) == null) {
            jsonObject.put("message", "用户不存在！");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        if (picUploadResult.getStatus().equals("done")) {
            if (picUploadResult.getName() != null) {
                System.out.println(picUploadResult.getName());
                userService.updatePicUrl(picUploadResult.getName(), username);
                jsonObject.put("message", "上传成功");
                jsonObject.put("pic_url", picUploadResult.getName());
                return new ResponseEntity(jsonObject, HttpStatus.OK);
            }
        }
        jsonObject.put("message", "未知漏洞，请联系作者");
        return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
    }
}
