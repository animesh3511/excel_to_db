package com.example.excel_to_db.repository;

import com.example.excel_to_db.Model.Excel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcelRepository extends JpaRepository<Excel,Long> {


}
