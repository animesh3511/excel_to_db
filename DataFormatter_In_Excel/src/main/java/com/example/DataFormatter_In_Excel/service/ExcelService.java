package com.example.DataFormatter_In_Excel.service;

import java.io.IOException;
import java.io.InputStream;

public interface ExcelService {


    Object saveDataFromExcel(InputStream inputStream) throws IOException;
}
