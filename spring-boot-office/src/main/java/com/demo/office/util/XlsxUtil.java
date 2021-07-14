package com.demo.office.util;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

/**
 导出xlsx文档的工具类，基本跟隔壁的XlsUtil一模一样
 在看下面具体方法/类前，你要了解xlsx的结构（这些结构你经常用到，但不一定确切认识）
 直接看main方法，介绍一些类和方法，实际方法类自己写，因为想了想，没有统一规定xlsx的格式，也不好写工具类的方法

 至于表标题（表头），表数据，这些xlsx格式的问题，自己确定格式咯
 * */
public class XlsxUtil {

	public static void main(String[] args) throws IOException {
		//创建HSSFWorkbook对象，就是整个xls
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();

		//创建HSSFSheet对象，就是sheet，一个xls可以创建多个sheet
		XSSFSheet xssfSheet1 = xssfWorkbook.createSheet("sheet1");
		XSSFSheet xssfSheet2 = xssfWorkbook.createSheet("sheet2");
		//设置整个sheet的默认行高（n*20，其中20就是行高的计数单位），列宽（不知道计数单位）
		//下面行高30*20中30是30磅，列宽50不知道对应啥，反正这里格式要求就是单元格能展示单元格内的全部内容（或者是不要太挤）
		//再不济你也不用设置，下载xls文档的那个人会自己去调整符合他自己习惯的格式（又不是什么大事）
		xssfSheet1.setDefaultRowHeight((short) (30*20));
		xssfSheet1.setDefaultColumnWidth(50);
		xssfSheet2.setDefaultRowHeight((short) (100*20));
		xssfSheet2.setDefaultColumnWidth(100);

		//创建HSSFRow对象，就是行，一个sheet可以创建多行
		//行从0开始计数
		XSSFRow xssfRow0 = xssfSheet1.createRow(0);
		XSSFRow xssfRow2 = xssfSheet1.createRow(2);
		XSSFRow xssfRow1 = xssfSheet1.createRow(1);

		//创建HSSFCell对象，单元格，就是行坐标和列坐标的确定的单元格
		//我在这里的命名习惯就是 hssfCell[行坐标]_[列坐标]
		XSSFCell xssfCell0_0 = xssfRow0.createCell(0);
		XSSFCell xssfCell1_1 = xssfRow1.createCell(1);
		XSSFCell xssfCell2_2 = xssfRow2.createCell(2);

		//设置单元格的值
		xssfCell0_0.setCellValue("我在单元格1_A");
		xssfCell1_1.setCellValue("我在单元格2_B");
		xssfCell2_2.setCellValue("我在单元格3_C");

		//单元格格式，具体方法自己看，xls不会有人去关注格式吧？
		XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平居中
		cellStyle.setVerticalAlignment(XSSFCellStyle.ALIGN_CENTER);//垂直居中
		XSSFFont fontStyle = xssfWorkbook.createFont();
		fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		fontStyle.setFontHeightInPoints((short)16);  //设置标题字体大小
		cellStyle.setFont(fontStyle);
		xssfCell0_0.setCellStyle(cellStyle);

		//合并单元格，在sheet2这个sheet上操作
		//指定 4 个参数，起始行，结束行，起始列，结束列。然后这个区域将被合并。
		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 1, 0, 2);
		xssfSheet2.addMergedRegion(cellRangeAddress);

		OutputStream outputStream = new FileOutputStream("D:\\cwm\\spring-boot-demo\\spring-boot-office\\src\\main\\resources\\xlsx1.xlsx");
		xssfWorkbook.write(outputStream);
	}




}
