package com.schoolmanagerserver.service.Impl;


import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.service.FileService;
import com.schoolmanagerserver.utils.OssProperties;
import org.springframework.web.multipart.MultipartFile;


public class FileServiceImpl implements FileService {

    private OssProperties ossProperties;


    @Override
    public Result<Void> deleteFile(String fileName) {

        return Result.success(); // 删除成功
    }

    @Override
    public Result<String> upload(MultipartFile file, String path) {
        // 调用 OSS SDK 上传文件
        // 获取上传后的文件路径
        String uploadedFilePath = "";
        return Result.success(uploadedFilePath);
    }

}