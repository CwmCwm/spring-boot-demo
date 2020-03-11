# spring-boot-security


## 本项目知识点
1. spring-security是什么  
2. 准备条件  
3. spring-security的默认配置
4. spring-security的 用户-角色-api-菜单
5. spring-security的其他使用场景，如OAuth2，jwt等等 TODO 



## 内容 
### 1. spring-security是什么  
百度了解更多  
就是各种认证，登录认证，接口权限，OAuth2，jwt  等等    
关于安全/认证那可是个大方向，能写很多东西  
场景也是相当多  


### 2. 准备条件  
MySQL/MariaDB数据库，之前docker 安装过了
数据库脚本见/resources/sql/ 目录


### 3. spring-security的默认配置
#### form表单验证  
使用场景：浏览器，前后端  
/login (get)：登录页面，任意没有登录的请求都会跳转到这里，就是上面看到的那个页面。  
我不用这个security提供的登录页面，因为前后端分离  

/login (post)：登录接口，在登录页面点击登录，会请求这个接口。  
form-data数据格式  
字段为 username 和 password  
前后端分离，前端自行编写登录页面，然后ajax提交，根据返回数据进行前端交互  

/login?error：用户名或密码错误，跳转到该页面。  
这里不用这个，而是通过配置 http.formLogin().failureForwardUrl("/login/fail"); 转发请求到对应Controller，自定义处理  
这样也方便前后端分离  

/：登录成功后，默认跳转的页面，/会重定向到index.html，这个页面要你自己实现。  
我不用，因为上面说了前后端分离啊  
这里通过配置 http.formLogin().successForwardUrl("/login/success"); 转发请求到对应Controller，自定义处理  

/logout：注销接口  
注销  

/login?logout：注销成功跳转页面。  
我不用  

上面这些是form表单验证的默认配置，因为前后端分离，所以都是使用数据接口返回，不会返回页面html    


#### httpBasic验证  
使用场景：后端应用互相调用，后端没有保存cookie嘛，所以直接访问接口
具体使用百度咯   


以上测试，我用postMan测试，工具自己选  


### 4. spring-security的 用户-角色-api-菜单  
表设计见 数据库脚本见/resources/sql/ 目录  
4个表+3个关联表，看完表设计都知道逻辑  
代码实现见代码    

看完这个示例  
可以debug看下spring security的流程图  
[spring security的流程图](./README-RESOURCES/spring-security流程图.vsdx)




