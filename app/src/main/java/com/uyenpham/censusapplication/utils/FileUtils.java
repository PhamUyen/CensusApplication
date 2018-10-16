package com.uyenpham.censusapplication.utils;

import android.content.Context;

import com.uyenpham.censusapplication.models.locality.NationDTO;
import com.uyenpham.censusapplication.models.locality.ReligionDTO;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static List<String[]> getContentFromAsset(String fileName, Context context){
        List<String[]> csvLine = new ArrayList<>();
        String[] content = null;
        try {
            InputStream inputStream =  context.getAssets().open(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = br.readLine()) != null){
                content = line.split(",");
                csvLine.add(content);
            }
            br.close();
        } catch (IOException e) {
            Logger.e(e);
        }
        return csvLine;
    }

    public static List<ReligionDTO> getListReligion(Context context){
        ArrayList<ReligionDTO> listReligion = new ArrayList<>();
        List<String[]> csvLine = getContentFromAsset("DMTG.xlsx",context);
        for(int i =0; i< csvLine.size(); i++){
            String[] row = csvLine.get(i);
            ReligionDTO religionDTO = new ReligionDTO(row[0], row[1]);
            listReligion.add(religionDTO);
        }
        return listReligion;
    }
    public static List<NationDTO> getListNation(Context context){
        ArrayList<NationDTO> listReligion = new ArrayList<>();
        try{
            InputStream  myInput = context.getAssets().open("DMDANTOC.xlsx");
//            Workbook myWorkBook = WorkbookFactory.create(myInput);
//
//            // Get the first sheet from workbook
//            Sheet mySheet = myWorkBook.getSheetAt(0);
////            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
//            /** We now need something to iterate through the cells.**/
//            Iterator<Row> rowIterator = mySheet.rowIterator();
//            DataFormatter dataFormatter = new DataFormatter();
//
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//
//                // Now let's iterate over the columns of the current row
//                Iterator<Cell> cellIterator = row.cellIterator();
//                NationDTO religionDTO = new NationDTO();
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    String cellValue = dataFormatter.formatCellValue(cell);
//                    if(cell.getColumnIndex() == 0){
//                        religionDTO.setId(cellValue);
//                    }else {
//                        religionDTO.setName(cellValue);
//                    }
//                }
//                listReligion.add(religionDTO);
//            }
//            myWorkBook.close();
            try {
                XSSFWorkbook workbook = new XSSFWorkbook(myInput);
                XSSFSheet sheet = workbook.getSheetAt(0);
                int rowsCount = sheet.getPhysicalNumberOfRows();
                FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
                for (int r = 0; r<rowsCount; r++) {
                    Row row = sheet.getRow(r);
                    NationDTO religionDTO = new NationDTO();
                    int cellsCount = row.getPhysicalNumberOfCells();
                    for (int c = 0; c<cellsCount; c++) {
                        Cell cell = row.getCell(c);
                        String value = getCellAsString(cell, formulaEvaluator);
                        if(cell.getColumnIndex() == 0){
                        religionDTO.setId(value);
                    }else {
                        religionDTO.setName(value);
                    }
                    }
                    listReligion.add(religionDTO);
                }
            } catch (Exception e) {
                /* proper exception handling to be here */
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listReligion;
    }
    protected static String getCellAsString(Cell cell, FormulaEvaluator formulaEvaluator) {
        String value = "";
        try {

            CellValue cellValue = formulaEvaluator.evaluate(cell);
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = ""+cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    double numericValue = cellValue.getNumberValue();
                    if(HSSFDateUtil.isCellDateFormatted(cell)) {
                        double date = cellValue.getNumberValue();
                        SimpleDateFormat formatter =
                                new SimpleDateFormat("dd/MM/yy");
                        value = formatter.format(HSSFDateUtil.getJavaDate(date));
                    } else {
                        value = ""+numericValue;
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = ""+cellValue.getStringValue();
                    break;
                default:
            }
        } catch (NullPointerException e) {
            /* proper error handling should be here */
//            printlnToUser(e.toString());
        }
        return value;
    }
}
