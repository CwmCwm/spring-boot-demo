package com.demo.office.util;


import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 docx文件的操作工具类
 这个docx文档虽然是xml结构，但是代码写起来真的累
 * */
public class DocxUtil {

	//看看 XWPFDocument 怎么使用，你可以实验效果如何，如doc文档的图片/表格/链接等等
	//这个debug看XWPFDocument的数据结构，就是xml格式
	public static void main(String[] args) {
		File file = new File("E:\\gitWorkspace\\spring-boot-demo\\spring-boot-office\\src\\main\\resources\\docx1.docx");
		try {
			FileInputStream fileInputStream = fileInputStream = new FileInputStream(file);
			XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);

			//将doc文档中的字符串"${name}" 替换成 "cwm"，这个场景经常用到，这里只替换文本，原来的格式维持不变
			Map<String, String> replaceMap = new HashMap<>();
			replaceMap.put("${name}", "cwm");
			replaceMap.put("${birthday}", "1970-01-01");
			//你debug会看到，下面的段落XWPFParagraph还会进行单词拆分XWPFRun，这里 ${abc_xyz} 在docx文档中会拆成 "${abc_" 和 "xyz}"
			//但是我在docx文档的table中也使用了${abc_xyz} ，就不会拆分单词了，这个分词规则无法捉摸啊
			replaceMap.put("${abc_xyz}", "123_456");
			replaceMap.put("${abcd_xyz}", "1234_567");
			replaceMap.put("${gggABC}", "111222");
			replaceMap.put("${helloWorldAreYouOk}", "1111111111111111111111");

			//获取所有段落，遍历段落中的文本，替换占位符
			List<XWPFParagraph> xwpfParagraphs = xwpfDocument.getParagraphs();
			for(XWPFParagraph paragraph : xwpfParagraphs) {
				//遍历获取段落中所有的runs
				List<XWPFRun> xwpfRuns = paragraph.getRuns();
				//变量替换逻辑
				for (int i = 0; i < xwpfRuns.size(); i++) {
					XWPFRun xwpfRun = xwpfRuns.get(i);
					String text = xwpfRun.getText(xwpfRuns.get(i).getTextPosition());
					if (text != null) {
						boolean isSetText = false;
						for (Map.Entry<String, String> entry : replaceMap.entrySet()) {
							String key = entry.getKey();
							if (text.indexOf(key) != -1) {
								isSetText = true;
								Object value = entry.getValue();
								if (value instanceof String) {
									//文本替换
									text = text.replace(key, value.toString());
								}
							}
						}
						if (isSetText) {
							xwpfRun.setText(text, 0);
						}
					}
				}
			}

			//上面只是段落中占位符的处理，docx中表格中的占位符没有处理的，还要在循环处理表格中的占位符
			//至于docx文档中其他类型，我也不知道（自己debug咯），如公式/形状等等，反正场景不会这么复杂吧，处理段落和表格的占位符足以应付很多场景了
			//下面设置table中的值好麻烦啊，从table后去row，从row获取cell，再设置cell中的值
			List<XWPFTable> xwpfTables = xwpfDocument.getTables();
			for (XWPFTable xwpfTable : xwpfTables) {
				for (XWPFTableRow xwpfTableRow : xwpfTable.getRows()) {
					for (XWPFTableCell xwpfTableCell : xwpfTableRow.getTableCells()) {
						String text = xwpfTableCell.getText();
						if (text != null) {
							CTTc ctTc = xwpfTableCell.getCTTc();
							CTP ctP = ctTc.sizeOfPArray() == 0 ? ctTc.addNewP() : ctTc.getPArray(0);
							XWPFParagraph par = new XWPFParagraph(ctP, xwpfTableCell);
							//遍历获取段落中所有的runs
							List<XWPFRun> xwpfRuns = par.getRuns();
							//变量替换逻辑
							for (int i = 0; i < xwpfRuns.size(); i++) {
								XWPFRun xwpfRun = xwpfRuns.get(i);
								String runtext = xwpfRun.getText(xwpfRuns.get(i).getTextPosition());
								if (runtext != null) {
									boolean isSetText = false;
									for (Map.Entry<String, String> entry : replaceMap.entrySet()) {
										String key = entry.getKey();
										if (runtext.indexOf(key) != -1) {
											isSetText = true;
											Object value = entry.getValue();
											if (value instanceof String) {
												//文本替换
												runtext = runtext.replace(key, value.toString());
											}
										}
									}
									if (isSetText) {
										xwpfRun.setText(runtext, 0);
									}
								}
							}
						}
					}
				}
			}

			//操作完doc文档后，导出新的doc文档
			xwpfDocument.write(new FileOutputStream("E:\\gitWorkspace\\spring-boot-demo\\spring-boot-office\\src\\main\\resources\\docx2.docx", true));
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
		File file = new File(sourceDoc);
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
