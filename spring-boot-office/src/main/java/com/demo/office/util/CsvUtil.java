package com.demo.office.util;


import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.io.*;

/**
 导出csv文档的工具类
 这个最简单了，就是字符串拼接，一般用","分隔字段，"\r\n"作为换行
 最常用的数据导出文档，既适合非开发人员（如客服/销售）看和上传，也适合软件之间交换数据（以前有些软件就是这么交互的，现在都是走http接口了）
 * */
public class CsvUtil {

	public static void main(String[] args) throws IOException {
		//表头和表数据
		StringBuilder header = new StringBuilder("姓名,性别,出生日期\r\n");
		StringBuilder body = new StringBuilder();
		for (int i=0; i<100; i++) {
			body.append("姓名" + i + ",");
			body.append("男,");
			body.append("1990-01-01");
			body.append("\r\n");
		}
		StringBuilder csv = header.append(body);

		String path = "D:\\cwm\\spring-boot-demo\\spring-boot-office\\src\\main\\resources\\csv1.csv";
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
		out.write(csv.toString());
		out.close();
	}




}
