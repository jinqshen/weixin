package com.jinqshen.weixin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.jinqshen.weixin.pojo.table.StudentInfo;

public class StudentInfoExcelUtil extends BaseExcelUtil<StudentInfo> {

	@Override
	protected List<StudentInfo> readExcelValue(Workbook wb) throws Exception {
		Sheet sheet = wb.getSheetAt(0);
		System.out.println("sheet:" + sheet);
		this.totalRows = sheet.getPhysicalNumberOfRows();
		System.out.println("总行数:" + totalRows);
		if(totalRows > 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("总列数:" + totalCells);
		}
		List<StudentInfo> list = new ArrayList<>();
		for(int i = 1;i < totalRows;i++) {
			Row row = sheet.getRow(i);
			if(row == null) {
				continue;
			}
			StudentInfo studentInfo = new StudentInfo();
			for(int j = 0;j < totalCells;j++) {
				Cell cell = row.getCell(j);
				if(cell != null) {
					switch (j) {
					case 0:
						if(cell.getCellType() == CellType.NUMERIC) {
							 int student_number = (int) cell.getNumericCellValue();
							 studentInfo.setStudent_number(student_number);
						}
						break;
					case 1:
						if(cell.getCellType() == CellType.STRING) {
							String name = cell.getStringCellValue();
							studentInfo.setName(name);
						}
						break;
					case 2:
						if(cell.getCellType() == CellType.STRING) {
							String sex = cell.getStringCellValue();
							studentInfo.setSex(sex);
						}
						break;
					case 3:
						if(cell.getCellType() == CellType.STRING) {
							String major = cell.getStringCellValue();
							studentInfo.setMajor(major);
						}
						break;
					case 4:
						if(cell.getCellType() == CellType.STRING) {
							String academy = cell.getStringCellValue();
							studentInfo.setAcademy(academy);
						}
						break;
					case 5:
						if(cell.getCellType() == CellType.NUMERIC) {
							Date birthday = cell.getDateCellValue();
							java.sql.Date birth = new java.sql.Date(birthday.getTime());
							studentInfo.setBirth(birth);;
						}
						break;
					case 6:
						if(cell.getCellType() == CellType.STRING) {
							String id_number = cell.getStringCellValue();
							studentInfo.setId_number(id_number);;
						}
						break;
					default:
						break;
					}
				}
			}
			list.add(studentInfo);
		}
		return list;
	}

}
