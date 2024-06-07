package com.example.DataFormatter_In_Excel.serviceimpl;

import com.example.DataFormatter_In_Excel.model.Excel;
import com.example.DataFormatter_In_Excel.repository.ExcelRepository;
import com.example.DataFormatter_In_Excel.service.ExcelService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    ExcelRepository excelRepository;

    @Override
    public Object saveDataFromExcel(InputStream inputStream) throws IOException {

        // here we created instance of workbook using WorkbookFactory
        Workbook workbook = WorkbookFactory.create(inputStream);
        //here we created instance of Sheet using workbook.getSheetAt() method
        Sheet sheet = workbook.getSheetAt(0);
//here we take iterator using sheet.iterator() method
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        List<Excel> list = new ArrayList<>();

        //dataFormatter instance
        DataFormatter dataFormatter = new DataFormatter();

        while(rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            Excel excel = new Excel();

            //here we took value at cell '0' and converted it to string using dataformatter
            String id = dataFormatter.formatCellValue(row.getCell(0));
           //here we converted string "id" to Long id
            excel.setCompanyId(Long.valueOf(id));
            //dataformatter return us the value in string format
            excel.setCompanyName(dataFormatter.formatCellValue(row.getCell(1)));
            excel.setDescription(dataFormatter.formatCellValue(row.getCell(2)));
            excel.setWebsite(dataFormatter.formatCellValue(row.getCell(3)));
            excel.setContact(dataFormatter.formatCellValue(row.getCell(4)));
            excel.setDate(dataFormatter.formatCellValue(row.getCell(5)));
            excel.setPrice(dataFormatter.formatCellValue(row.getCell(6)));
            excel.setPercentage(dataFormatter.formatCellValue(row.getCell(7)));

           list.add(excel);

        }

        excelRepository.saveAll(list);
        return "data saved succesfully";
    }
}
