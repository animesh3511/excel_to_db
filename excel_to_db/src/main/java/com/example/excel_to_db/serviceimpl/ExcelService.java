package com.example.excel_to_db.serviceimpl;

import com.example.excel_to_db.Model.Excel;
import com.example.excel_to_db.repository.ExcelRepository;
import com.example.excel_to_db.service.ExcelInterface;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelService implements  ExcelInterface
{
   @Autowired
    ExcelRepository excelRepository;



    @Override
    public Object saveDataFromExcel(InputStream inputStream) throws IOException {

        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();

        List<Excel> list = new ArrayList<>();

        while(rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            Excel excel = new Excel();

            excel.setCompanyId((long) row.getCell(0).getNumericCellValue());
            excel.setCompanyName(row.getCell(1).getStringCellValue());
            excel.setDescription(row.getCell(2).getStringCellValue());
            excel.setWebsite(row.getCell(3).getStringCellValue());
            excel.setContact(row.getCell(4).getNumericCellValue());

            list.add(excel);


        }



       excelRepository.saveAll(list);
       return "data saved succesfully";
    }

    @Override
    public Object exportdataToExcel(HttpServletResponse response) throws IOException {

       List<Excel> list = excelRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Excel Data");

         Row headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue("company_Id");
        headerRow.createCell(1).setCellValue("company_Name");
        headerRow.createCell(2).setCellValue("contact");
        headerRow.createCell(3).setCellValue("Description");
        headerRow.createCell(4).setCellValue("Website");

      int rowNum = 1;

      for(Excel excel:list)
      {
          Row row = sheet.createRow(rowNum++);
          row.createCell(0).setCellValue(excel.getCompanyId());
          row.createCell(1).setCellValue(excel.getCompanyName());
          String contactString = String.format("%.0f", excel.getContact());
          row.createCell(2).setCellValue(contactString);
          row.createCell(3).setCellValue(excel.getDescription());
          row.createCell(4).setCellValue(excel.getWebsite());

      }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=excel_data.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

        return "excel sheet downloaded";
    }

    @Override
    public boolean isExcelFile(MultipartFile file) {
        String contentType = file.getContentType();

        if (contentType != null && contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
        {
            return true;
        }
        else {
            return  false;


        }
    }



}
