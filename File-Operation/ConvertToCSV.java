class ConverToCsv(){
 // convert the XLSX file to a CSV file.
 // Can respond only XLSX file.
 private boolean createCsvFromXLSX(String excelFilePath) {
        FileInputStream in = null;
        BufferedWriter bw = null;
        Workbook book = null;
        StringBuffer inBuffer = null;
        try {
            in = new FileInputStream(excelFilePath);
            book = new XSSFWorkbook(in);
            in.close();
            bw = new BufferedWriter(new FileWriter(csvFilePath, true));

            for (int i = 0; i < book.getNumberOfSheets(); ++i) {
                Sheet sheet = book.getSheetAt(i);
                for (Row row : sheet) {
                    // Exclude the first line(category).
                    // Avoid trouble of Null line of automate generated XLSX file.
                    if (row.getRowNum() == 0 || this.isNullRow(row)) {
                        continue;
                    }
                    inBuffer = new StringBuffer("");
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j, Row.RETURN_NULL_AND_BLANK);
                        inBuffer.append(this.getCellStr(cell).trim());
                        inBuffer.append(",");
                    }
                    // Add category name.
                    // When the sheet name to the category.
                    inBuffer.append(sheet.getSheetName().trim());
                    bw.write(inBuffer.toString());
                    bw.newLine();
                }
            }
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
                if (null != bw) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    boolean isNullRow(Row row)
    {
        boolean result = true;
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (!"".equals(this.getCellStr(cell))) {
                result = false;
                break;
            }
        }
        return result;
    }
    
    String getCellStr(Cell cell) {
        if (null == cell || "".equals(cell)) {
            return "";
        }
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_BOOLEAN:
            return Boolean.toString(cell.getBooleanCellValue());
        case Cell.CELL_TYPE_FORMULA:
            return cell.getCellFormula();
        case Cell.CELL_TYPE_NUMERIC:
            return Integer.toString((int) cell.getNumericCellValue());
        case Cell.CELL_TYPE_STRING:
            return cell.getStringCellValue();
        case Cell.CELL_TYPE_BLANK:
            return "";
        }
        return "";
    }
  }
