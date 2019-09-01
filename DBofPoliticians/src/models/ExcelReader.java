package models;


import com.sun.rowset.internal.Row;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelReader {
    private String filePath;
    private Workbook workbook;
    private int sheetNumber;


    public ExcelReader(String filePath, int sheetNumber) throws IOException {
        this.filePath = filePath;
        this.sheetNumber = sheetNumber;
        File file = new File(filePath);
        workbook = WorkbookFactory.create(file);
        SheetSaverToDB saverToDB = new SheetSaverToDB(fetchSheet(), sheetNumber);
        saverToDB.saveSheetToDb();
    }

    public ArrayList<ArrayList<String>> fetchSheet(){
        ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
        Sheet sheet = workbook.getSheetAt(sheetNumber);
        for (int i = 2; i < sheet.getLastRowNum() + 1; i++) {
            lists.add(new ArrayList<>());
            for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                String data = new DataFormatter().formatCellValue(sheet.getRow(i).getCell(j));
                lists.get(i-2).add(data);
            }
        }
        return lists;
    }
}