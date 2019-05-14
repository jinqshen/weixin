package com.jinqshen.weixin.utils;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


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

	/**
	 *将指定集合导出为Excel文件
	 * @param headers 列标题
	 * @param dataset 数据集
	 * @param fileName 文件名
	 * @param response response响应
	 */
	public void exportExcel(String[] headers, Collection<T> dataset, String fileName, HttpServletResponse response) {
		// 声明一个工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 生成一个表格
		XSSFSheet sheet = workbook.createSheet(fileName);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 20);
		// 产生表格标题行
		XSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			XSSFCell cell = row.createCell(i);
			XSSFRichTextString text = new XSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		try {
			// 遍历集合数据，产生数据行
			Iterator<T> it = dataset.iterator();
			int index = 0;
			while (it.hasNext()) {
				index++;
				row = sheet.createRow(index);
				T t = (T) it.next();
				// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
				Field[] fields = t.getClass().getDeclaredFields();
				for (short i = 0; i < headers.length; i++) {
					XSSFCell cell = row.createCell(i);
					Field field = fields[i];
					String fieldName = field.getName();
					String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					// 其它数据类型都当作字符串简单处理
					if(value != null && value != ""){
						textValue = value.toString();
					}
					if (textValue != null) {
						XSSFRichTextString richString = new XSSFRichTextString(textValue);
						cell.setCellValue(richString);
					}
				}
			}
			getExportedFile(workbook, fileName,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * 方法说明: 指定路径下生成EXCEL文件
	 * @return
	 */
	public void getExportedFile(XSSFWorkbook workbook, String name,HttpServletResponse response) throws Exception {
		BufferedOutputStream fos = null;
		try {
			String fileName = name + ".xlsx";
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" ));
			fos = new BufferedOutputStream(response.getOutputStream());
			workbook.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}
}
