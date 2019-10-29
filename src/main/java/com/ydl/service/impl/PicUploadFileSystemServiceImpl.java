package com.ydl.service.impl;

import com.ydl.entity.PicUploadResult;
import com.ydl.service.PicUploadFileSystemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class PicUploadFileSystemServiceImpl implements PicUploadFileSystemService {
    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg",
            ".jpeg", ".gif", ".png"};
    public PicUploadResult upload(MultipartFile uploadFile, String username) {
        //校验图片格式
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(),
                    type)) {
                isLegal = true;
                break;
            }
        }
        // 封装Result对象，并且将文件的byte数组放置到result对象中
        PicUploadResult fileUploadResult = new PicUploadResult();
        if (!isLegal) {
            fileUploadResult.setStatus("noexpected");
            return fileUploadResult;
        }
        // 文件新路径
        String fileName = uploadFile.getOriginalFilename();
        System.out.println(fileName);
        String filePath = getFilePath(fileName,username);
        System.out.println("filepath:"+filePath);
        // 生成图片的绝对引用地址
        String picUrl = StringUtils.replace(StringUtils.substringAfter(filePath,
                "F:\\code\\test-upload\\images"),
                "\\", "/");
        System.out.println(StringUtils.substringAfter(filePath,
                "F:/code/test-upload/images"));
        //访问路径
        fileUploadResult.setName("localhost:8888/upload" + picUrl);
        File newFile = new File(filePath);
        // 写文件到磁盘
        try {
            uploadFile.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            //上传失败
            fileUploadResult.setStatus("error");
            return fileUploadResult;
        }
        fileUploadResult.setStatus("done");
        fileUploadResult.setUid(String.valueOf(System.currentTimeMillis()));
        return fileUploadResult;
    }
    public String getFilePath(String sourceFileName,String username) {
        String baseFolder = "F:\\code\\test-upload" + File.separator
                + "images";
        System.out.println(File.separator);
        Date nowDate = new Date();
        // yyyy/MM/dd
        System.out.println("baseFolder :"+baseFolder);
        String fileFolder = baseFolder + File.separator +username;
        File file = new File(fileFolder);
        System.out.println("fileFolder:"+fileFolder);

        if (!file.isDirectory()) {
            // 如果目录不存在，则创建目录
            file.mkdirs();
        }
        System.out.println(StringUtils.substringAfterLast(sourceFileName, "."));
        // 生成新的文件名
        String fileName = "icon"+ "." +
                StringUtils.substringAfterLast(sourceFileName, ".");
        return fileFolder + File.separator + fileName;
    }
}
