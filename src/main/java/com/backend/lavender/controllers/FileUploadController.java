package com.backend.lavender.controllers;

import com.backend.lavender.models.ResponseObject;
import com.backend.lavender.services.IStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path="/api/v1/FileUpload")
public class FileUploadController {
    @Autowired
    private IStorageService storageService;
    @PostMapping("")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file") MultipartFile file){
        try {
            String generatedFileName=storageService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "upload image file successfully",generatedFileName)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("ok", e.getMessage(), "")
            );
            //TODO: handle exception
        }
    }
}
