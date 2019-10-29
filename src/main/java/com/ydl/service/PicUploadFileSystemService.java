package com.ydl.service;

import com.ydl.entity.PicUploadResult;
import org.springframework.web.multipart.MultipartFile;



public interface PicUploadFileSystemService {

    PicUploadResult upload(MultipartFile uploadFile,String username);

    String getFilePath(String sourceFileName,String suername);
}
