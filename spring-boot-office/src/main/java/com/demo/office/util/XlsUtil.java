package com.demo.office.util;


import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.*;

/**
 导出xls文档的工具类
 在看下面具体方法/类前，你要了解xls的结构（这些结构你经常用到，但不一定确切认识）
 直接看main方法，介绍一些类和方法，实际方法类自己写，因为想了想，没有统一规定xls的格式，也不好写工具类的方法

 至于表标题（表头），表数据，这些xls格式的问题，自己确定格式咯
 * */
public class XlsUtil {

	public static void main(String[] args) throws IOException {
		//创建HSSFWorkbook对象，就是整个xls
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

		//创建HSSFSheet对象，就是sheet，一个xls可以创建多个sheet
		HSSFSheet hssfSheet1 = hssfWorkbook.createSheet("sheet1");
		HSSFSheet hssfSheet2 = hssfWorkbook.createSheet("sheet2");
		//设置整个sheet的默认行高（n*20，其中20就是行高的计数单位），列宽（不知道计数单位）
		//下面行高30*20中30是30磅，列宽50不知道对应啥，反正这里格式要求就是单元格能展示单元格内的全部内容（或者是不要太挤）
		//再不济你也不用设置，下载xls文档的那个人会自己去调整符合他自己习惯的格式（又不是什么大事）
		hssfSheet1.setDefaultRowHeight((short) (30*20));
		hssfSheet1.setDefaultColumnWidth(50);
		hssfSheet2.setDefaultRowHeight((short) (100*20));
		hssfSheet2.setDefaultColumnWidth(100);

		//创建HSSFRow对象，就是行，一个sheet可以创建多行
		//行从0开始计数
		HSSFRow hssfRow0 = hssfSheet1.createRow(0);
		HSSFRow hssfRow2 = hssfSheet1.createRow(2);
		HSSFRow hssfRow1 = hssfSheet1.createRow(1);

		//创建HSSFCell对象，单元格，就是行坐标和列坐标的确定的单元格
		//我在这里的命名习惯就是 hssfCell[行坐标]_[列坐标]
		HSSFCell hssfCell0_0 = hssfRow0.createCell(0);
		HSSFCell hssfCell1_1 = hssfRow1.createCell(1);
		HSSFCell hssfCell2_2 = hssfRow2.createCell(2);

		//设置单元格的值
		hssfCell0_0.setCellValue("我在单元格1_A");
		hssfCell1_1.setCellValue("我在单元格2_B");
		hssfCell2_2.setCellValue("我在单元格3_C");

		//单元格格式，具体方法自己看，xls不会有人去关注格式吧？
		HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);//垂直居中
		HSSFFont fontStyle = hssfWorkbook.createFont();
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontStyle.setFontHeightInPoints((short)16);  //设置标题字体大小
		cellStyle.setFont(fontStyle);
		hssfCell0_0.setCellStyle(cellStyle);

		//合并单元格，在sheet2这个sheet上操作
		//指定 4 个参数，起始行，结束行，起始列，结束列。然后这个区域将被合并。
		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 1, 0, 2);
		hssfSheet2.addMergedRegion(cellRangeAddress);

		OutputStream outputStream = new FileOutputStream("D:\\cwm\\spring-boot-demo\\spring-boot-office\\src\\main\\resources\\xls1.xls");
		hssfWorkbook.write(outputStream);
	}



}
