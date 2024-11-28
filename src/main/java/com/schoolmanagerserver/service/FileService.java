package com.schoolmanagerserver.service;

import com.schoolmanagerserver.pojos.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {

    public Result<Void> deleteFile(String fileName);

    public Result<String> upload(MultipartFile file, String path) ;
}