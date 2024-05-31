package com.example.excel_to_db.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public interface ExcelInterface {

   boolean isExcelFile(MultipartFile file);

   Object saveDataFromExcel(InputStream inputStream) throws IOException;

   Object exportdataToExcel(HttpServletResponse response) throws IOException;



}
