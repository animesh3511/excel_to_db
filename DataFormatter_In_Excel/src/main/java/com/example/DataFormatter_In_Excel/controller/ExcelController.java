package com.example.DataFormatter_In_Excel.controller;

import com.example.DataFormatter_In_Excel.model.response.CustomEntityResponse;
import com.example.DataFormatter_In_Excel.model.response.EntityResponse;
import com.example.DataFormatter_In_Excel.service.ExcelService;
import javassist.bytecode.stackmap.BasicBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/api")
public class ExcelController {

    @Autowired
    ExcelService excelService;


    @PostMapping("/saveDataFromExcel")
    public ResponseEntity<?> saveDataFromExcel(@RequestPart("file")MultipartFile file)
    {
    if (file.isEmpty())
    {

        return new ResponseEntity(new CustomEntityResponse("file is empty",0), HttpStatus.OK);

    }

 try {

     return new ResponseEntity(new EntityResponse(excelService.saveDataFromExcel(file.getInputStream()),0),HttpStatus.OK);

 }catch (Exception e)
 {

     return new ResponseEntity(new CustomEntityResponse(e.getMessage(),-1),HttpStatus.OK);
 }





    }



}
