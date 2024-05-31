package com.example.excel_to_db.controller;

import com.example.excel_to_db.Model.response.CustomEntityResponse;
import com.example.excel_to_db.Model.response.EntityResponse;
import com.example.excel_to_db.serviceimpl.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @PostMapping("/saveDataFromExcel")
    public ResponseEntity<?> saveDataFromExcel(@RequestPart("file")MultipartFile file) {

        if(file.isEmpty())
        {
            return new ResponseEntity(new CustomEntityResponse("file is empty",0),HttpStatus.OK);


        }

        try {

            return new ResponseEntity(new EntityResponse(excelService.saveDataFromExcel(file.getInputStream()), 0), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.OK);


        }

    }


    @PostMapping("/isExcelFile")
    public ResponseEntity<?> isExcelFile(@RequestPart("file")MultipartFile file) {

        if(file.isEmpty())
        {
            return new ResponseEntity(new CustomEntityResponse("file is empty",0),HttpStatus.OK);


        }

        try {

            return new ResponseEntity(new EntityResponse(excelService.isExcelFile(file), 0), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.OK);


        }

    }

    @GetMapping("/exportdataToExcel")
    public ResponseEntity<?> exportdataToExcel(HttpServletResponse response)
    {
        try{

            return new ResponseEntity(new EntityResponse(excelService.exportdataToExcel(response),0),HttpStatus.OK);

        }catch (Exception e)
        {

          return new ResponseEntity(new CustomEntityResponse(e.getMessage(),-1),HttpStatus.OK);

        }




    }





}
