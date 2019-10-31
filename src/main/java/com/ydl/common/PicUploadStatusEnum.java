package com.ydl.common;

public enum PicUploadStatusEnum {
    DEFAULT(-9,"ERROR"),
    PIC_NOTEXPECT(-1,"请上传符合格式的图片"),
    NAME_NOTEXIST(-2,"用户不存在"),
    UPLOAD_ERROR(-3,"上传失败"),
    UPLOAD_SUCCESS(1,"上传成功");


    private int picUploadStatus;

    private String name;

    PicUploadStatusEnum(int picUploadStatus, String name) {
        this.picUploadStatus = picUploadStatus;
        this.name = name;
    }

    public static PicUploadStatusEnum getPicUploadStatusEnum(int picUploadStatus){
        for(PicUploadStatusEnum picUploadStatusEnum : PicUploadStatusEnum.values()){
            if(picUploadStatusEnum.getPicUploadStatus() == picUploadStatus){
                return picUploadStatusEnum;
            }
        }
        return DEFAULT;
    }

    public int getPicUploadStatus() {
        return picUploadStatus;
    }

    public void setPicUploadStatus(int picUploadStatus) {
        this.picUploadStatus = picUploadStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
