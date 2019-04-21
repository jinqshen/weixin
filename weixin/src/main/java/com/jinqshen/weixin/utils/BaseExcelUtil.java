package com.jinqshen.weixin.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;


public abstract class BaseExcelUtil<T> {

	//总行数
	protected int totalRows = 0;
	//总列数
	protected int totalCells = 0;
	
	private String errorMsg;
	
	public int getTotalRows() {
		return totalRows;
	}

	public int getTotalCells() {
		return totalCells;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * 读取Excel文件，获取文件信息转化为集合
	 * @param mfile
	 * @return
	 */
	public List<T> getExcelInfo(MultipartFile mfile){
		String filename = mfile.getOriginalFilename();
		System.out.println("文件名为：" + filename);
		List<T> list = null;
		try {
			if(!validateExcel(filename))
				return null;
			boolean isExcel2003 = true;
			if(isExcel2007(filename))
				isExcel2003 = false;
			list = createExcel(mfile.getInputStream(),isExcel2003);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据Excel里的内容获取信息
	 * @param inputStream
	 * @param isExcel2003
	 * @return
	 */
	protected List<T> createExcel(InputStream inputStream, boolean isExcel2003) {
		List<T> list = null;
		try {
			Workbook wb = null;
			if(isExcel2003)
				wb = new HSSFWorkbook(inputStream);
			else {
				wb = new XSSFWorkbook(inputStream);
			}
			list = readExcelValue(wb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 传入Excel对象，对于信息进行遍历并封装至集合中
	 * @param wb
	 * @return
	 * @throws Exception 
	 */
	protected abstract List<T> readExcelValue(Workbook wb) throws Exception;

	/**
	 * 验证文件是否为Excel文件
	 * @param filePath
	 * @return
	 */
	public boolean validateExcel(String filePath) {
		if(filePath != null && (isExcel2003(filePath) || isExcel2007(filePath))) {
			
			return true;
		}
		errorMsg = "该文件不是Excel文件";
		System.out.println(errorMsg);
		return false;
	}
	
	/**
	 * 判断Excel文件是否为2003版本
	 * @param filePath
	 * @return
	 */
	public static boolean isExcel2003(String filePath) {
		boolean result = filePath.matches("^.+\\.(?i)(xls)$");
		return result;
	}
	
	/**
	 * 判断Excel文件是否为2007版本
	 * @param filePath
	 * @return
	 */
	public static boolean isExcel2007(String filePath) {
		boolean result = filePath.matches("^.+\\.(?i)(xlsx)$");
		return result;
	}
}
