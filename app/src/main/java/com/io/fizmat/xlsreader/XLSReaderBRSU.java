package com.io.fizmat.xlsreader;

import com.io.fizmat.xlsreader.model.Curs;
import com.io.fizmat.xlsreader.model.Day;
import com.io.fizmat.xlsreader.model.Group;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;

public class XLSReaderBRSU {

    private HSSFWorkbook book;


    XLSReaderBRSU(HSSFWorkbook book)
    {
        this.book = book;
    }

    public static XLSReaderBRSU newInstance(InputStream in) throws IOException {
        HSSFWorkbook book = new HSSFWorkbook(in);
        return new XLSReaderBRSU(book);
    }

    public List<Curs> readXLS() throws IOException {
       List<Curs> list = new ArrayList<>();
       for (int i = 0; i < book.getNumberOfSheets(); i++) {
           list.add(readsheat(book.getSheetAt(i)));
       }

       book.close();
       return list;
    }


    public Curs readsheat(HSSFSheet sheet) throws IOException {
        Curs curs = new Curs();
        String nameSheet = sheet.getSheetName();
        nameSheet = nameSheet.contains("КУРС")?nameSheet.toLowerCase():nameSheet;
        List<Group> groupList = createListGroup(sheet.getRow(3),nameSheet);
        curs.setGroupList(groupList);

        curs.setCursTitle(nameSheet);

        HSSFCell cell;
        HSSFRow row;

        List<CellRangeAddress> regionsList = getListRabgeAddress(sheet);

        int count = -1;
        for (int k = 4; k < 34; k++) {
            if (k % 5 == 4)
            {
                count++;
            }
            row = sheet.getRow(k);
            for (int i = 1; i < groupList.size() + 1 ; i++) {
                Group group = groupList.get(i - 1);
                cell = row.getCell(i);
                if (cell != null) {
                    boolean mergedRegion = true;
                    for (CellRangeAddress region : regionsList) {
                        if (region.isInRange(cell.getRowIndex(), cell.getColumnIndex())) {
                            mergedRegion = false;
                            int rowNum = region.getFirstRow();
                            int colIndex = region.getFirstColumn();
                            cell = sheet.getRow(rowNum).getCell(colIndex);
                            group.addTimeTableString(count, formaterString(cell));
                        }
                    }
                    if (mergedRegion) {
                        group.addTimeTableString(count, formaterString(cell));
                    }
                }
                else
                {
                    group.addTimeTableString(count, "");
                }
            }
        }
      //  curs.setOwerIdList();

        return curs;
    }

    private String formaterString(HSSFCell cell)
    {
        String string = " ";
            CellType type = cell.getCellTypeEnum();

            if (type.equals(CellType.STRING)) {
                string = ParseString.corectStringRead(cell.getStringCellValue());
            }

            if (type.equals(CellType.FORMULA)) {
                Date date = cell.getDateCellValue();
                string = date.toString().split(" ")[2] + MonthRus.getRusMonth(date.getMonth());
            }
        return string;
    }

    private List<Group> createListGroup(HSSFRow row, String curcTitle)
    {
        List<Group> groupList = new ArrayList<>();

        for (int i = 1; i < row.getLastCellNum(); i++) {
            String title = row.getCell(i).getStringCellValue();
            if (title.contains("Веб") || title.contains("ФМ")) break;
            Group group = new Group(title,curcTitle);
          //  System.out.println(row.getCell(i).getStringCellValue());
            List<Day> dayList = new ArrayList<>();
            dayList.add(new Day());
            dayList.add(new Day());
            dayList.add(new Day());
            dayList.add(new Day());
            dayList.add(new Day());
            dayList.add(new Day());
            group.setDayList(dayList);
            groupList.add(group);
            if (title.equals("КФ-21") || title.equals("3 КУРС")) break;
        }
        int endPosition = groupList.size() - 1;
        if (groupList.get(endPosition).getNameGroup().contains("ФИ"))
            groupList.remove(endPosition);


        return groupList;
    }

    private List<CellRangeAddress> getListRabgeAddress(HSSFSheet sheet)
    {
        List<CellRangeAddress> regionsList = new ArrayList<>();
        for (int j = 0; j < sheet.getNumMergedRegions(); j++) {
            regionsList.add(sheet.getMergedRegion(j));
        }
        return regionsList;
    }
}
