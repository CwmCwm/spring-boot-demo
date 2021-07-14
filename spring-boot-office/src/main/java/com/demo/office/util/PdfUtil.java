package com.demo.office.util;


import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.util.Map;
import java.util.Set;

/**
 doc/docx文档转pdf的工具类
 * */
public class PdfUtil {

	public static void main(String[] args) {
		System.out.println(docToPdf("D:\\cwm\\spring-boot-demo\\spring-boot-office\\src\\main\\resources\\doc1.doc", "D:\\cwm\\spring-boot-demo\\spring-boot-office\\src\\main\\resources\\doc1.pdf"));
		System.out.println(docxToPdf("D:\\cwm\\spring-boot-demo\\spring-boot-office\\src\\main\\resources\\docx1.docx", "D:\\cwm\\spring-boot-demo\\spring-boot-office\\src\\main\\resources\\docx1.pdf"));
	}

	/**
	 doc转pdf
	 @param sourceDocPath  doc原文档的路径
	 @param targetDocPath  doc导出文档的路径
	 @return 成功或失败
	 */
	public static boolean docToPdf(String sourceDocPath, String targetDocPath) {
		boolean result = false;
		File inputFile = new File(sourceDocPath);
		File outputFile = new File(targetDocPath);
		OutputStream outputStream = null;
		InputStream inputStream =null;
		try {
			inputStream = new FileInputStream(inputFile);
			outputStream= new FileOutputStream(outputFile);
			IConverter converter = LocalConverter.builder().build();
			converter.convert(inputStream).as(DocumentType.DOC).to(outputStream).as(DocumentType.PDF).execute();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 docx转pdf
	 @param sourceDocxPath  docx原文档的路径
	 @param targetDocxPath  docx导出文档的路径
	 @return 成功或失败
	 */
	public static boolean docxToPdf(String sourceDocxPath, String targetDocxPath) {
		boolean result = false;
		File inputFile = new File(sourceDocxPath);
		File outputFile = new File(targetDocxPath);
		OutputStream outputStream = null;
		InputStream inputStream =null;
		try {
			inputStream = new FileInputStream(inputFile);
			outputStream= new FileOutputStream(outputFile);
			IConverter converter = LocalConverter.builder().build();
			converter.convert(inputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


}
