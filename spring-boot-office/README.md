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
代码见 `com.demo.office.util.DocUtil`   


### 2. docx文档的占位符替换   
resources/docx1.docx  是文档格式      
com.demo.office.util.DocxUtil  是工具类   
docx文档的占位符替换就很麻烦了，因为docx文档是以xml的数据结构来存储的，这个xml的DOM树就很繁琐               
要知道docx文档的xml组织结构，你看工具类就知道了          
还有一个坑，就是docx文档会对单词进行分词， 你需要下面这样操作     
WPS软件  选项->拼写检查->自定义词典（取消勾选），选项->拼写检查（这里的所有检查都不要勾选）      
这样就不会对单词进行分词，就不会出现将${abc_xyz} 在docx文档中会拆成 "${abc_" 和 "xyz}"）         
代码见 `com.demo.office.util.DocxUtil`


### 3. doc/docx文档转化为pdf文档   
只想给pdf这种文档（不想给doc/docx文档）    
浏览器想预览doc/docx文档，直接将doc/docx文档转化为pdf文档，浏览器用个iframe展示就可以了（简单）           
代码见 `com.demo.office.util.PdfUtil`   


### 4. xls文档的生成   
实际生产中的场景都写了（其他方法自己看看呗），或者我遇到就补充呗       
代码见 `com.demo.office.util.xlsUtil`   


### 5. xlsx文档的生成
代码基本更上面的xls文档的生成一模一样   
代码见 `com.demo.office.util.XlsxUtil`   


### 6. csv文档   
单sheet，简单的数据导出，不需要格式，直接用csv    
这个最简单了，就是字符串拼接，一般用","分隔字段，"\r\n"作为换行
最常用的数据导出文档，既适合非开发人员（如客服/销售）看和上传，也适合软件之间交换数据（以前有些软件就是这么交互的，现在都是走http接口了）
代码见 `com.demo.office.util.CsvUtil`






总结：   
上面代码有是本地文件导出，浏览器的文件返回（你自己写了，无非就是请求加对应类型，返回文件流，不难的）       
推荐用doc文档，不推荐用docx文档，反正都能设置格式/打印，肯定用简单的工具类   




