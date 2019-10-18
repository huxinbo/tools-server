package com.example.toolsserver.common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    private static final ZoneId PK_ZONE_ID = ZoneId.of("Asia/Karachi");

    public static String displayDateTimeAtPk(Instant instant) {
        if (instant == null) {
            return null;
        }

        return instant.atZone(PK_ZONE_ID).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static Double displayWithScale2(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return null;
        }

        return bigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static class CellWrapper {
        final private Cell cell;

        public CellWrapper(Cell cell) {
            this.cell = cell;
        }

        public void setCellStyle(CellStyle cellStyle) {
            if (this.cell != null) {
                this.cell.setCellStyle(cellStyle);
            }
        }

        public void setCellValue(String value) {
            if (this.cell != null) {
                this.cell.setCellValue(value);
            }
        }
    }

    public interface CellWriter<T> {
        void write(CellWrapper header, XSSFCell body, T data);
    }

    public static <T> void writeXSSF(XSSFSheet sheet, List<T> data, List<CellWriter<T>> writers) {
        writeXSSF(sheet, 0, data, writers);
    }

    private static <T> void writeXSSF(XSSFSheet sheet, int rowOffset, List<T> data, List<CellWriter<T>> writers) {
        List<CellWrapper> headerCells = new ArrayList<>();
        CellWrapper emptyCell = new CellWrapper(null);

        if (rowOffset == 0) {
            XSSFRow header = sheet.createRow(0);
            for (int i = 0; i < writers.size(); ++i) {
                XSSFCell cell = header.createCell(i);
                headerCells.add(new CellWrapper(cell));
            }
        }

        for (int i = 0; i < data.size(); ++i) {
            int rowIndex = i + rowOffset;
            XSSFRow row = sheet.createRow(1 + rowIndex);
            T thisRowData = data.get(i);

            for (int j = 0; j < writers.size(); ++j) {
                CellWrapper headerCell = emptyCell;
                if (rowIndex == 0) {
                    headerCell = headerCells.get(j);
                }

                XSSFCell cell = row.createCell(j);
                writers.get(j).write(headerCell, cell, thisRowData);
            }
        }
    }

    public static void writeXSSF(HttpServletResponse response, XSSFWorkbook workbook, String filename) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-disposition", "attachment;filename=" + filename + ".xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
