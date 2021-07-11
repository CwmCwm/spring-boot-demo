# spring-boot-office

## 场景说明   
合同用doc文档/docx文档编辑保存，其中有些变量需要根据当时情况进行替换      
有些业务说明书用doc文档/docx文档编辑保存，其中有些变量需要根据当时情况进行替换，最后转化为pdf格式提供给客户                 
系统积累一些数据，如订单/用户等等，需要将这些数据导出成xls文档/xlsx文档/csv文档             
还有其他的场景吗？ （你会用xls文档/xlsx文档编辑合同/业务说明书这类书面文档？）     



## 本项目知识点
1. doc文档的占位符替换  
2. docx文档的占位符替换  
3. doc/docx文档转化为pdf文档   
4. xls文档的生成   
5. xlsx文档的生成    
6. csv文档   


## 内容
### 1. doc文档的占位符替换  
resources/doc1.doc  是文档格式      
com.demo.office.util.DocUtil  是工具类   
doc文档的占位符替换很简单，代码不多，也没什么坑        


### 2. docx文档的占位符替换   
resources/docx1.docx  是文档格式      
com.demo.office.util.DocxUtil  是工具类   
docx文档的占位符替换就很麻烦了，因为docx文档是以xml的数据结构来存储的，这个xml的DOM树就很繁琐               
要知道docx文档的xml组织结构，你看工具类就知道了          
还有一个坑，就是docx文档会对单词进行分词（WPS软件  选项->拼写检查->自定义词典（取消勾选），这样就不会对单词进行分词，就不会出现将${abc_xyz} 在docx文档中会拆成 "${abc_" 和 "xyz}"）         

总结，推荐用doc文档，不用docx文档，反正都能打印，肯定用简单的工具类     






