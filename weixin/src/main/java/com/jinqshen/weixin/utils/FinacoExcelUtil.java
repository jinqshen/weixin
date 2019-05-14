package com.jinqshen.weixin.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.jinqshen.weixin.pojo.table.Finaco;

public class FinacoExcelUtil extends BaseExcelUtil<Finaco> {

	@Override
	protected List<Finaco> readExcelValue(Workbook wb) throws Exception {
		Sheet sheet = wb.getSheetAt(0);
		System.out.println("sheet:" + sheet);
		this.totalRows = sheet.getPhysicalNumberOfRows();
		System.out.println("总行数:" + totalRows);
		if(totalRows > 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("总列数:" + totalCells);
		}
		List<Finaco> list = new ArrayList<>();
		for(int i = 1;i < totalRows;i++) {
			Row row = sheet.getRow(i);
			if(row == null) {
				continue;
			}
			Finaco finaco = new Finaco();
			finaco.setFinaco_no(0);
			finaco.setTest_result(0.0f);
			for(int j = 0;j < totalCells;j++) {
				Cell cell = row.getCell(j);
				if(cell != null) {
					switch (j) {
					case 0:
						if(cell.getCellType() == CellType.NUMERIC) {
							 int student_number = (int) cell.getNumericCellValue();
							 finaco.setStudent_number(student_number);
						}
						break;
					case 1:
						if(cell.getCellType() == CellType.NUMERIC) {
							Integer project_no = (int) cell.getNumericCellValue();
							finaco.setProject_no(project_no);;
						}
						break;
					case 2:
						if(cell.getCellType() == CellType.STRING) {
							String semester = cell.getStringCellValue();
							finaco.setSemester(semester);
						}
						break;
					case 3:
						if(cell.getCellType() == CellType.STRING) {
							String grade = cell.getStringCellValue();
							finaco.setGrade(grade);
						}
						break;
					case 4:
						if(cell.getCellType() == CellType.STRING) {
							String test_result_describe = cell.getStringCellValue();
							finaco.setTest_result_describe(test_result_describe);
						}
						break;
					default:
						break;
					}
				}
			}
			list.add(finaco);
		}
		return list;
	}

}
