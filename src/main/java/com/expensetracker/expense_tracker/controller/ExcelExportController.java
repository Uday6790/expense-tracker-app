package com.expensetracker.expense_tracker.controller;

import com.expensetracker.expense_tracker.model.Expense;
import com.expensetracker.expense_tracker.repository.ExpenseRepository;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExcelExportController {

    private final ExpenseRepository expenseRepository;

    public ExcelExportController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> exportExcel() {

        try {

            // Get all expenses
            List<Expense> expenses =
                    expenseRepository.findAll();

            // Create Excel workbook
            Workbook workbook =
                    new XSSFWorkbook();

            // Create sheet
            Sheet sheet =
                    workbook.createSheet("Expenses");

            // =========================
            // HEADER STYLE
            // =========================

            CellStyle headerStyle =
                    workbook.createCellStyle();

            Font headerFont =
                    workbook.createFont();

            headerFont.setBold(true);

            headerStyle.setFont(headerFont);

            // =========================
            // HEADER ROW
            // =========================

            Row header =
                    sheet.createRow(0);

            header.createCell(0)
                    .setCellValue("ID");

            header.createCell(1)
                    .setCellValue("Description");

            header.createCell(2)
                    .setCellValue("Amount");

            header.createCell(3)
                    .setCellValue("Category");

            header.createCell(4)
                    .setCellValue("Date");

            // Apply header style
            for (int i = 0; i < 5; i++) {

                header.getCell(i)
                        .setCellStyle(headerStyle);
            }

            // =========================
            // EXPENSE DATA
            // =========================

            int rowNumber = 1;

            for (Expense expense : expenses) {

                Row row =
                        sheet.createRow(rowNumber++);

                row.createCell(0)
                        .setCellValue(
                                expense.getId()
                        );

                row.createCell(1)
                        .setCellValue(
                                expense.getDescription()
                        );

                row.createCell(2)
                        .setCellValue(
                                expense.getAmount()
                                        .doubleValue()
                        );

                row.createCell(3)
                        .setCellValue(
                                expense.getCategory()
                        );

                row.createCell(4)
                        .setCellValue(
                                expense.getDate()
                                        .toString()
                        );
            }

            // =========================
            // AUTO SIZE COLUMNS
            // =========================

            for (int i = 0; i < 5; i++) {

                sheet.autoSizeColumn(i);
            }

            // =========================
            // CONVERT TO BYTE ARRAY
            // =========================

            ByteArrayOutputStream outputStream =
                    new ByteArrayOutputStream();

            workbook.write(outputStream);

            workbook.close();

            byte[] excelFile =
                    outputStream.toByteArray();

            // =========================
            // DOWNLOAD RESPONSE
            // =========================

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=expenses.xlsx"
                    )
                    .contentType(
                            MediaType.APPLICATION_OCTET_STREAM
                    )
                    .body(excelFile);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }
}