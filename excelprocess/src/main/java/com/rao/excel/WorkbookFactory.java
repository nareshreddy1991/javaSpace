package com.rao.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class WorkbookFactory {

    public static Workbook getWorkbook() throws IOException {
        FileInputStream inputStream = null;
        try {
            File file = new File("G:\\learning\\excel\\ReportFormat1.xls");
            inputStream = new FileInputStream(file);
            if (file.getName().endsWith(".xlsx")) {
                return new XSSFWorkbook(inputStream);
            } else {
                return new HSSFWorkbook(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
        return null;
    }

    public static Workbook createWorkbook(Workbook workbook) {
        if (workbook instanceof XSSFWorkbook) {
            return new XSSFWorkbook();
        } else {
            return new HSSFWorkbook();
        }
    }

    public static void writeHeaders(List<String> headerList, Sheet newSheet, Cell firstCell) {
        CellStyle sourceCellStyle = firstCell.getCellStyle();
        Row newRow = newSheet.createRow(0);
        headerList.add("");
        headerList.add("STATUS");
        headerList.add("REASON FOR FAILURE");
        Iterator<String> iterator = headerList.iterator();

        int colNum = 0;
        while (iterator.hasNext()) {
            String header = iterator.next();
            Cell newCell = newRow.createCell(colNum++);
            newCell.setCellValue(header);
            CellStyle cellStyle = newCell.getSheet().getWorkbook().createCellStyle();
            cellStyle.cloneStyleFrom(sourceCellStyle);
            newCell.setCellStyle(cellStyle);
        }

    }

    public static  void cloneStyle(Cell source, Cell target){
        CellStyle sourceCellStyle = source.getCellStyle();
        CellStyle targetCellStyle = target.getSheet().getWorkbook().createCellStyle();
        targetCellStyle.cloneStyleFrom(sourceCellStyle);
        target.setCellStyle(targetCellStyle);
    }

    public static void writeWorkbook(Workbook workbook) {
        try {
            FileOutputStream out = new FileOutputStream(new File("G:\\learning\\excel\\ReportFormat1_out.xls"));
            workbook.write(out);
            out.close();
            System.out.println("Successfully written to new file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void formatCell(Cell cell, Boolean result) {
//        CellStyle style = cell.getCellStyle();
//        if (style == null) {
        CellStyle style = cell.getSheet().getWorkbook().createCellStyle();
//        }
//        style.setFillBackgroundColor(IndexedColors.BLACK.index);
//        style.setFillPattern(FillPatternType.BIG_SPOTS);
//        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.BIG_SPOTS);
        cell.setCellStyle(style);
    }
}
