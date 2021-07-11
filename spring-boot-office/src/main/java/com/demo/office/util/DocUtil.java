package com.demo.office.util;


import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Fields;
import org.apache.poi.hwpf.usermodel.Range;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;

/**
 doc文件的操作工具类
 * */
public class DocUtil {

	//看看 HWPFDocument 怎么使用，你可以实验效果如何，如doc文档的图片/表格/链接等等
	public static void main(String[] args) {
		File file = new File("E:\\gitWorkspace\\spring-boot-demo\\spring-boot-office\\src\\main\\resources\\doc1.doc");
		try {
			FileInputStream fileInputStream = fileInputStream = new FileInputStream(file);
			HWPFDocument hwpfDocument = new HWPFDocument(fileInputStream);

			//通过 Range 去操作doc文档，Range提供前插入，后插入，替换（其实最有用是替换，其他随意）
			Range range = hwpfDocument.getRange();

			//在doc文档开头插入字符串"Document"，设置该字符串"Document"的样式
//			CharacterRun characterRun1 = range.insertBefore("Document");
//			characterRun1.setFontSize(2 * 12);
//			characterRun1.setBold(true);
//			characterRun1.setItalic(true);
//			characterRun1.setColor(111);
//			characterRun1.setData(true);

			//将doc文档中的字符串"${name}" 替换成 "cwm"，这个场景经常用到，这里只替换文本，原来的格式维持不变
			//你debug看下 range 是什么，下面${abc_xyz}保持原样，不像docx文档，会对单词进行拆分，看 DocxUtil工具类注释
			range.replaceText("${name}", "cwm");
			range.replaceText("${birthday}", "1970-01-01");
			range.replaceText("${abc_xyz}", "123456");

			//操作完doc文档后，导出新的doc文档
			hwpfDocument.write(new FileOutputStream("E:\\gitWorkspace\\spring-boot-demo\\spring-boot-office\\src\\main\\resources\\doc2.doc", true));
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 渲染doc文档，其实就是替换doc文档中的占位符
	 上面main方法看过了，下面你都可以按照自己的定义去写
	 sourceDoc      存在的doc模板的路径，如合同模板
	 replaceMap     替换的值，key是占位符，value是替换后的值
	 exportDoc      导出的doc的路径
	 */
	public static void replaceTextAndSave(String sourceDoc, Map<String, String> replaceMap, String exportDoc) {
		try {
			FileInputStream fileInputStream = fileInputStream = new FileInputStream(sourceDoc);
			FileOutputStream fileOutputStream = new FileOutputStream(exportDoc, true);
			HWPFDocument hwpfDocument = new HWPFDocument(fileInputStream);
			Range range = hwpfDocument.getRange();
			Set<Map.Entry<String, String>> set = replaceMap.entrySet();
			for (Map.Entry<String, String> item : set) {
				range.replaceText(item.getKey(), item.getValue());
			}
			hwpfDocument.write(fileOutputStream);
			fileInputStream.close();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
